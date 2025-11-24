package com.erp.controller;

import com.erp.repository.dto.ItemOrderDTO;
import com.erp.service.ItemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ItemOrderController {


    @Autowired
    private ItemOrderService itemOrderService;

    @GetMapping("/test")
    public String test(Model model) {

        List<ItemOrderDTO> itemOrder = itemOrderService.getAllItemOrder();

        model.addAttribute("ItemOrder", itemOrder);
        return "itemOrderHistory";
    }
}
