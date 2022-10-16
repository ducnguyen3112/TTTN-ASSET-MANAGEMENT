package com.nashtech.assetmanagement.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestAssetRequestDto {
    String categoryId;
    String note;
    int quantity;
}