package com.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StockControllerTest {

    /**
     * 품목 재고 조회 : 본사 화면
     *  - templates/stock/storeItemManagerUI.html
     */
    @GetMapping("/manager/storeItem")
    public String storeItemStockManager(@RequestParam(defaultValue = "1") Long storeNo,
                                        Model model) {

        model.addAttribute("role", "본사");
        model.addAttribute("storeNo", storeNo);

        // ★ resources/templates/stock/storeItemManagerUI.html
        return "stock/storeItemManagerUI";
    }

    /**
     * 품목 재고 조회 : 직영점 화면
     *  - templates/stock/storeItemStoreUI.html
     */
    @GetMapping("/store/storeItem")
    public String storeItemStockStore(@RequestParam(defaultValue = "1") Long storeNo,
                                      Model model) {

        model.addAttribute("role", "직영");
        model.addAttribute("storeNo", storeNo);

        // ★ resources/templates/stock/storeItemStoreUI.html
        return "stock/storeItemStoreUI";
    }

    /**
     * 재고 변동 조회 : 본사 화면
     *  - templates/stock/storeStockManagerUI.html
     */
    @GetMapping("/manager/storeStock")
    public String storeStockHistoryManager(@RequestParam(defaultValue = "1") Long storeNo,
                                           Model model) {

        model.addAttribute("role", "본사");
        model.addAttribute("storeNo", storeNo);
        return "stock/storeStockManagerUI";
    }

    /**
     * 재고 변동 조회 : 직영점 화면
     *  - templates/stock/storeStockStoreUI.html
     */
    @GetMapping("/store/storeStock")
    public String storeStockHistoryStore(@RequestParam(defaultValue = "1") Long storeNo,
                                         Model model) {

        model.addAttribute("role", "직영");
        model.addAttribute("storeNo", storeNo);
        return "stock/storeStockStoreUI";
    }
}
