package com.erp.controller;

import com.erp.repository.StoreItemRepository;
import com.erp.repository.dto.StoreItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StoreItemControllerTest {

    private final StoreItemRepository storeItemRepository;


    @GetMapping("/storeItem/stock")
    public String storeItemStock(@RequestParam(defaultValue = "직영") String role,
                                 @RequestParam(defaultValue = "1") Long storeNo,
                                 Model model) {

        List<StoreItemDTO> storeItems =
                storeItemRepository.findStoreItemsByStoreNo(storeNo);

        model.addAttribute("role", role);
        model.addAttribute("storeNo", storeNo);
        model.addAttribute("storeItems", storeItems);


        return "storeItemUI";
    }
}
