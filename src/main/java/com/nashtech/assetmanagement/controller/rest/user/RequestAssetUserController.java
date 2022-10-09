package com.nashtech.assetmanagement.controller.rest.user;

import com.nashtech.assetmanagement.dto.request.RequestAssetRequestDto;
import com.nashtech.assetmanagement.dto.response.MessageResponse;
import com.nashtech.assetmanagement.dto.response.RequestAssetListResponseDto;
import com.nashtech.assetmanagement.dto.response.RequestAssetResponseDto;
import com.nashtech.assetmanagement.service.RequestAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/api/request-assets")
public class RequestAssetUserController {

    private RequestAssetService requestAssetService;

    @Autowired
    public RequestAssetUserController(RequestAssetService requestAssetService) {
        this.requestAssetService = requestAssetService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequestAssetResponseDto createRequestForAsset(@RequestBody RequestAssetRequestDto requestAssetRequestDto) {
        return requestAssetService.createRequestForAsset(requestAssetRequestDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RequestAssetListResponseDto getListUserRequestForAsset(@RequestParam int pageSize,
                                                                  @RequestParam int page){
        return requestAssetService.getListUserRequestForAsset(pageSize, page);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse deleteRequestAsset(@PathVariable("id") Long id){
        return requestAssetService.deleteRequestAsset(id);
    }
}
