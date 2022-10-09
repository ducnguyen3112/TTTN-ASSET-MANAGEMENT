package com.nashtech.assetmanagement.repositories;

import com.nashtech.assetmanagement.entities.RequestAsset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestAssetRepository extends JpaRepository<RequestAsset, Long> {

    Page<RequestAsset> findAllByRequestedAssetBy_StaffCodeOrderByRequestedDateDesc(String userName, Pageable pageable);
}