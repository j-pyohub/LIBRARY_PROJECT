package com.erp.service;

import com.erp.repository.ItemOrderRepository;
import com.erp.repository.dto.ItemOrderDTO;
import com.erp.repository.entity.ItemOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemOrderService {
    private ItemOrderRepository repoOrder;

    @Transactional(readOnly = true)
    public List<ItemOrderDTO> getAllItemOrder() {
        List<ItemOrderDTO> itemOrder = new ArrayList<>();
        repoOrder.findAll().forEach(order -> itemOrder.add(ItemOrderDTO.toDTO(order)));

        return itemOrder;
    }

    public ItemOrderDTO getItemOrderById(int id) {
        return null;
    }
}
