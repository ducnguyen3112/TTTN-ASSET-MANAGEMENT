package com.nashtech.assetmanagement.service.impl;

import com.nashtech.assetmanagement.dto.request.RequestAssetRequestDto;
import com.nashtech.assetmanagement.dto.response.MessageResponse;
import com.nashtech.assetmanagement.dto.response.RequestAssetListResponseDto;
import com.nashtech.assetmanagement.dto.response.RequestAssetResponseDto;
import com.nashtech.assetmanagement.entities.Category;
import com.nashtech.assetmanagement.entities.RequestAsset;
import com.nashtech.assetmanagement.entities.Users;
import com.nashtech.assetmanagement.enums.RequestAssetState;
import com.nashtech.assetmanagement.mapper.RequestAssetMapper;
import com.nashtech.assetmanagement.repositories.CategoryRepository;
import com.nashtech.assetmanagement.repositories.RequestAssetRepository;
import com.nashtech.assetmanagement.repositories.UserRepository;
import com.nashtech.assetmanagement.service.AuthenticationServices;
import com.nashtech.assetmanagement.service.RequestAssetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Date;

@Service
public class RequestAssetServiceImpl implements RequestAssetService {

    private final RequestAssetRepository requestAssetRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final RequestAssetMapper requestAssetMapper;

    private final AuthenticationServices authenticationService;

    public RequestAssetServiceImpl(RequestAssetRepository requestAssetRepository
            , UserRepository userRepository, RequestAssetMapper requestAssetMapper
            , CategoryRepository categoryRepository, AuthenticationServices authenticationService) {
        this.requestAssetRepository = requestAssetRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.requestAssetMapper = requestAssetMapper;
        this.authenticationService = authenticationService;
    }

    @Override
    public RequestAssetResponseDto createRequestForAsset(RequestAssetRequestDto requestAssetRequestDto) {
        RequestAsset requestAsset = new RequestAsset();
        String userName =
                authenticationService.getUser().getUserName();
        Users users =
                userRepository.findByUserName(userName).orElseThrow(() ->
                        new NotFoundException("Can't find user with username: " + userName));
        requestAsset.setRequestedAssetBy(users);
        Category category =
                categoryRepository.findById(requestAssetRequestDto.getCategoryId()).orElseThrow(() ->
                        new NotFoundException("Can't find category with id: " + requestAssetRequestDto.getCategoryId()));
        requestAsset.setCategory(category);
        requestAsset.setQuantity(requestAssetRequestDto.getQuantity());
        requestAsset.setState(RequestAssetState.REQUEST_ASSET_WAITING_FOR_APPROVAL);
        requestAsset.setNote(requestAssetRequestDto.getNote());
        requestAsset.setRequestedDate(new Date());
        requestAsset = requestAssetRepository.save(requestAsset);
        RequestAssetResponseDto responseDto =
                requestAssetMapper.requestAssetToResponseDto(requestAsset);
        responseDto.setUserName(requestAsset.getRequestedAssetBy().getUserName());
        responseDto.setCategoryName(requestAsset.getCategory().getName());
        return responseDto;
    }

    @Override
    public RequestAssetListResponseDto getListUserRequestForAsset(int pageSize, int page) {
        String userName =
                authenticationService.getUser().getUserName();
        Users users =
                userRepository.findByUserName(userName).orElseThrow(() ->
                        new NotFoundException("Can't find user with username: " + userName));
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<RequestAsset> requestAssets =
                requestAssetRepository.findAllByRequestedAssetBy_StaffCodeOrderByRequestedDateDesc(users.getStaffCode(), pageable);
        return RequestAssetListResponseDto.builder()
                .total(requestAssets.getTotalElements())
                .currentPage(requestAssets.getNumber() + 1)
                .lastPage(requestAssets.getTotalPages())
                .perPage(requestAssets.getNumberOfElements())
                .requestAssetResponseDtos(requestAssetMapper.listRequestAssetToListResponseDto(requestAssets.getContent()))
                .build();
    }

    @Override
    public MessageResponse deleteRequestAsset(Long requestAssetId) {
        RequestAsset requestAsset =
                requestAssetRepository.findById(requestAssetId).orElseThrow(() -> new NotFoundException("Can't find request for asset with ID: " + requestAssetId));
        if (requestAsset.getState() != RequestAssetState.REQUEST_ASSET_WAITING_FOR_APPROVAL || requestAsset.getAssignment() != null) {
            return new MessageResponse(HttpStatus.BAD_REQUEST, "Can't not delete this " +
                    "request for asset", new Date());
        }
        requestAssetRepository.delete(requestAsset);
        return new MessageResponse(HttpStatus.OK, "Delete request for asset success",
                new Date());
    }
}
