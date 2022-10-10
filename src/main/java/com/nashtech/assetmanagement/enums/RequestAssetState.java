package com.nashtech.assetmanagement.enums;

public enum RequestAssetState {
    REQUEST_ASSET_WAITING_FOR_APPROVAL("Waiting for approval"),
    REQUEST_ASSET_APPROVED("Approved"),
    REQUEST_ASSET_REJECTED("Rejected");

    private final String fieldDescription;

    RequestAssetState(String value) {
        fieldDescription = value;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }
}
