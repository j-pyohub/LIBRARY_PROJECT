package com.erp.repository.dto;

import com.erp.repository.entity.ItemOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemOrderDTO {
    private long itemNo;
    private String storeName;
    private Timestamp requestDate;
    private Integer quantity;
    private Integer price;
    private String status;

    public static ItemOrderDTO toDTO(ItemOrder itemOrder) {
        return new ItemOrderDTO(
                itemOrder.getItemOrderNo(),
                itemOrder.getStoreNo().getStoreName(),
                itemOrder.getRequestDatetime(),
                itemOrder.getTotalItem(),
                itemOrder.getTotalAmount(),
                itemOrder.getItemOrderStatus()
        );
    }
}
