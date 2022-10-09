package com.nashtech.assetmanagement.entities;

import com.nashtech.assetmanagement.enums.RequestAssetState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "request_asset")
public class RequestAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "requested_date")
    private Date requestedDate;

    @Column
    private int quantity;

    @Column
    private String note;

    @Column
    private RequestAssetState state;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "requested_asset_by")
    private Users requestedAssetBy;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "asset_code"),
            @JoinColumn(name = "assigned_date"),
            @JoinColumn(name = "assigned_to")
    })
    private Assignment assignment;
}