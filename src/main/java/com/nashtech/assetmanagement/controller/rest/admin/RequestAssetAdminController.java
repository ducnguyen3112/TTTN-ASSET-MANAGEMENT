package com.nashtech.assetmanagement.controller.rest.admin;


import com.nashtech.assetmanagement.dto.response.RequestAssetResponseDto;
import com.nashtech.assetmanagement.service.RequestAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/request-assets")
public class RequestAssetAdminController {
    private final RequestAssetService requestAssetService;

    @Autowired
    public RequestAssetAdminController(RequestAssetService requestAssetService) {
        this.requestAssetService = requestAssetService;
    }

    @PutMapping("/states")
    public RequestAssetResponseDto changeStateRequestAsset(@RequestParam(name = "id") Long requestAssetId,
                                                           @RequestParam(name = "state") String state){
        return requestAssetService.changeStateRequestAsset(requestAssetId,state);
    }

}
