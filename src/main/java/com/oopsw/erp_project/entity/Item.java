package com.oopsw.erp_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemNo;
    private String itemCode;
    private String itemCategory;
    private String itemName;
    private String ingredientName;
    private String stockUnit;
    private String supplyUnit;
    private String supplier;
    private Integer itemPrice;
    private Integer convertStock;
    private String storageType;
    private String expirationType;
    private Integer expiration;
    private String itemImage;
    private String note;
    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp inDate;
    private Timestamp editDate;
    private Timestamp delDate;
}
