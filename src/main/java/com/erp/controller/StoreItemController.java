package com.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock")
public class StoreItemController {

    /**
     * 재고 조회 : 본사 화면
     * templates/stock/storeItemManagerUI.html
     */
    @GetMapping("/store-item/manager")
    public String storeItemManagerView() {
        return "stock/storeItemManagerUI";
    }

    /**
     * 재고 조회 : 직영점 화면
     * templates/stock/storeItemStoreUI.html
     */
    @GetMapping("/store-item/store")
    public String storeItemStoreView() {
        return "stock/storeItemStoreUI";
    }
}
