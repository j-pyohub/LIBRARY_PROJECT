package com.erp.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Setter
@Getter
@Table(name = "sales_order")
public class SalesOrder {

    @Id
    private long salesOrderNo;
    private long storeNo;
    private Date salesOrderDateTime;
    private Integer salesOrderAmount;
}
