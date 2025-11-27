package com.erp.dto;

import com.erp.repository.entity.ItemOrder;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemOrderDTO {
    private long itemOrderNo;
    private Long storeNo;
    private String storeName;
    private Timestamp requestDate;
    private Integer quantity;
    private Integer price;
    private String orderStatus;
    private String receiveStatus;

    public static ItemOrderDTO toDTO(ItemOrder itemOrder) {
        return ItemOrderDTO
                .builder()
                .itemOrderNo(itemOrder.getItemOrderNo())
                .requestDate(itemOrder.getRequestDatetime())
                .quantity(itemOrder.getTotalItem())
                .price(itemOrder.getTotalAmount())
                .orderStatus(itemOrder.getItemOrderStatus())
                .storeNo(itemOrder.getStoreNo().getStoreNo())
                .build();
    }
}
