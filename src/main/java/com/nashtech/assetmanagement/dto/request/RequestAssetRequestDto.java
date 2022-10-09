package com.nashtech.assetmanagement.dto.request;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

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