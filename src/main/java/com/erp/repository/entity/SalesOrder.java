package com.erp.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Setter
@Getter
@ToString
@Table(name = "sales_order")
public class SalesOrder {

    @Id
    private long salesOrderNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_no")
    private Store store;

    private Date salesOrderDatetime;
    private Integer salesOrderAmount;
}
