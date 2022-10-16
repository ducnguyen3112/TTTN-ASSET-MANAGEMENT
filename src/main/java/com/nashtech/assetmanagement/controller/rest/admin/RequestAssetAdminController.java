package com.nashtech.assetmanagement.controller.rest.admin;

import com.nashtech.assetmanagement.dto.request.RequestAssetRequestDto;
import com.nashtech.assetmanagement.dto.request.StateRequestAssetDto;
import com.nashtech.assetmanagement.dto.response.RequestAssetResponseDto;
import com.nashtech.assetmanagement.service.RequestAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/request-assets")
public class RequestAssetAdminController {

    private RequestAssetService requestAssetService;

    @Autowired
    public RequestAssetAdminController(RequestAssetService requestAssetService) {
        this.requestAssetService = requestAssetService;
    }

    @PutMapping("/{id}/state")
    @ResponseStatus(HttpStatus.OK)
    public RequestAssetResponseDto changeStateRequestAsset(@PathVariable(name = "id") Long requestAssetId,@RequestBody StateRequestAssetDto state) {
        return requestAssetService.changeStateRequestAsset(requestAssetId,state);
    }
}
