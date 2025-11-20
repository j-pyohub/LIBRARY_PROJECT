package com.oopsw.erp_project.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "store")
public class Store {
    @Id
    private Long storeNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_manager_id")
    private Manager manager;

    private String storeStatus;
    private String storeName;
    private String address;
    private String latitude;
    private String longitude;
    private Timestamp openedDate;
    private Timestamp closedDate;
    private String storePhoneNumber;
    private String storeImage;
    private String openTime;
    private String closeTime;
    private String menuStopRole;
}
