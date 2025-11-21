package com.oopsw.erp_project.vo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemOrderNo;

    private String managerId;
    private Long storeNo;
    private Integer totalItem;
    private Integer totalAmount;
    private String itemOrderStatus;

    @CreationTimestamp
    private Timestamp requestDatetime;
    private Timestamp processDatetime;
}
