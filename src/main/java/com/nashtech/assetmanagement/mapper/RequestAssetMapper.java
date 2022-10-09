package com.nashtech.assetmanagement.mapper;

import com.nashtech.assetmanagement.dto.response.RequestAssetResponseDto;
import com.nashtech.assetmanagement.entities.RequestAsset;
import com.nashtech.assetmanagement.enums.RequestAssetState;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestAssetMapper {

    private final ModelMapper mapper;

    public RequestAssetMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public RequestAssetResponseDto requestAssetToResponseDto(RequestAsset requestAsset){
        return mapper.map(requestAsset,RequestAssetResponseDto.class);
    }

    public List<RequestAssetResponseDto> listRequestAssetToListResponseDto(List<RequestAsset> requestAssets){
        List<RequestAssetResponseDto> responseDtos =
                requestAssets.stream().map(requestAsset ->
                {
                    RequestAssetResponseDto temp=mapper.map(requestAsset,
                            RequestAssetResponseDto.class);
                    temp.setCategoryName(requestAsset.getCategory().getName());
                    temp.setUserName(requestAsset.getRequestedAssetBy().getUserName());
                    if (requestAsset.getState()== RequestAssetState.REQUEST_ASSET_WAITING_FOR_APPROVAL){
                        temp.setState("Waiting for approval");
                    } else if (requestAsset.getState()== RequestAssetState.REQUEST_ASSET_REJECTED) {
                        temp.setState("Rejected");
                    }else{
                        temp.setState("Accepted");
                    }
                    return temp;
                }).collect(Collectors.toList());
                return responseDtos;
    }
}
