package com.erp.controller;

import com.erp.dto.ItemOrderDTO;
import com.erp.dto.ItemOrderDetailDTO;
import com.erp.service.ItemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ItemOrderRestController {
    @Autowired
    ItemOrderService itemOrderService;

    @GetMapping("/itemOrder/itemOrderList/{pageNo}")
    public Map<String, Object> itemOrderList(@PathVariable int pageNo) {
        Page<ItemOrderDTO> page = itemOrderService.getItemOrderList(pageNo);
        return Map.of(
                "list", page.getContent(),
                "totalPages", page.getTotalPages(),
                "pageNo", page.getNumber() + 1,
                "totalElement", page.getTotalElements()
        );
    }
    @GetMapping("/itemOrder/itemOrderDetail/{itemOrderNo}")
    public List<ItemOrderDetailDTO> itemOrderDetail(@PathVariable Long itemOrderNo) {
        return itemOrderService.getItemOrderDetailByOrderNo(itemOrderNo);
    }
}
