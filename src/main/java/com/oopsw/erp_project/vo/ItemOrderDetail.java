package com.oopsw.erp_project.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class ItemOrderDetail {
    @Id
    private Long itemOrderDetailNo;
    private Long itemNo;


    @ManyToOne
    @JoinColumn(name = "item_order_no")
    private ItemOrder itemOrder;

    private Integer orderDetailQuantity;
    private Integer orderDetailPrice;
    private Timestamp receiveDatetime;
    private Integer receiveQuantity;
}
