package com.erp.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"itemNo", "itemOrderNo"})
public class ItemOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemOrderDetailNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_no")
    private Item itemNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_order_no")
    private ItemOrder itemOrderNo;

    private Integer orderDetailQuantity;
    private Integer orderDetailPrice;
    private Timestamp receiveDatetime;
    private Integer receiveQuantity;
}
