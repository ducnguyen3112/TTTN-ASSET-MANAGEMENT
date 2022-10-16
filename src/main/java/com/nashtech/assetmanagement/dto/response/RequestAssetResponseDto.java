package com.nashtech.assetmanagement.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nashtech.assetmanagement.entities.Assignment;
import com.nashtech.assetmanagement.entities.Category;
import com.nashtech.assetmanagement.entities.Users;
import com.nashtech.assetmanagement.enums.RequestAssetState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestAssetResponseDto {
    private Long id;
    private String note;
    private int quantity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date requestedDate;
    private String state;
    private String userName;
    private String categoryName;
    private String categoryId;
}
