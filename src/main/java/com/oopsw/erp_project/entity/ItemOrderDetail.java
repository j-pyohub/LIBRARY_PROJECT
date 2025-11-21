package com.oopsw.erp_project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ItemOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemOrderDetailNo;

    private Long itemNo;
    private Long itemOrderNo;
    private Integer orderDetailQuantity;
    private Integer orderDetailPrice;
    private Timestamp receiveDatetime;
    private Integer receiveQuantity;
}
