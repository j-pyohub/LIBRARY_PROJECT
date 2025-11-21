package com.erp.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Setter
@Getter
@ToString(exclude = "manager")
@Table(name = "store")
public class Store {
    @Id
    @Column(name = "store_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeNo;
    @OneToOne
    @JoinColumn(name = "store_manager_id")
    private Manager manager;
    @Column(nullable = false)
    private String storeStatus;
    @Column(nullable = false)
    private String storeName;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String latitude;
    @Column(nullable = false)
    private String longitude;
    private Date openedDate;
    private Date closedDate;
    @Column(nullable = false)
    private String storePhoneNumber;
    private String storeImage;
    @Column(nullable = false)
    private String openTime;
    @Column(nullable = false)
    private String closeTime;
    @Column(nullable = false)
    private String menuStopRole;
}
