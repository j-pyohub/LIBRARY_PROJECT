package com.erp.service;

import com.erp.repository.*;
import com.erp.dto.ItemOrderDTO;
import com.erp.repository.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemOrderService {
    final private ItemOrderRepository repoOrder;
    final private ItemOrderDetailRepository orderDetailRepo;
    final private ItemProposalRepository proposalRepo;
    final private StoreRepository storeRepo;

    @Transactional(readOnly = true)
    public List<ItemOrderDTO> getAllItemOrder() {
        List<ItemOrderDTO> itemOrder = new ArrayList<>();
        repoOrder.findAll().forEach(order -> itemOrder.add(ItemOrderDTO.toDTO(order)));

        return itemOrder;
    }

    public List<ItemOrderDTO> getItemOrderList(Integer pageNo) {
        List<ItemOrderDTO> itemOrderList = new ArrayList<>();

        repoOrder.findAll().forEach((order) -> {
            ItemOrderDTO itemOrderDTO = ItemOrderDTO.toDTO(order);

            itemOrderDTO.setStoreName(order.getStoreNo().getStoreName());
            boolean isReceive = orderDetailRepo.existsByItemOrderNoAndReceiveDatetimeIsNull(1L));
            itemOrderDTO.setReceiveStatus(isReceive ? "입고대기" : "입고완료");

            itemOrderList.add(itemOrderDTO);
        });
    }
}
