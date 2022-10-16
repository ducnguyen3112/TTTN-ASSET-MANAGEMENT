package com.nashtech.assetmanagement.service;

import com.nashtech.assetmanagement.dto.request.RequestAssetRequestDto;
import com.nashtech.assetmanagement.dto.request.StateRequestAssetDto;
import com.nashtech.assetmanagement.dto.response.MessageResponse;
import com.nashtech.assetmanagement.dto.response.RequestAssetListResponseDto;
import com.nashtech.assetmanagement.dto.response.RequestAssetResponseDto;

import java.util.List;

public interface RequestAssetService {
    RequestAssetResponseDto createRequestForAsset(RequestAssetRequestDto requestAssetRequestDto);

    RequestAssetListResponseDto getListUserRequestForAsset(int pageSize, int page);

    MessageResponse deleteRequestAsset(Long requestAssetId);

    RequestAssetResponseDto changeStateRequestAsset(Long requestAssetId, StateRequestAssetDto state);
}
