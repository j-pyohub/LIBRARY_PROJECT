package com.oopsw.erp_project.vo;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Item {
    @Id
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
    private String expiration;
    private String itemImage;
    private String note;

    @CreationTimestamp
    private Timestamp inDate;

    private Date editDate;
    private Date delDate;
}
