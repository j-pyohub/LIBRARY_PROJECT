package com.erp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/item")
public class ItemControllerTest {

    /** 재고 품목 목록 조회 */
    @GetMapping("/get")
    public String itemGet(Model model) {
        model.addAttribute("role", "본사");
        return "item/itemUI";
    }

    /** 재고 품목 상세 조회 */
    @GetMapping("/detail")
    public String itemDetail(Model model) {
        model.addAttribute("role", "본사");
        return "item/itemDetailUI";
    }

    /** 재고 품목 등록 화면 */
    @GetMapping("/add")
    public String itemAdd(Model model) {
        model.addAttribute("role", "본사");
        return "item/itemAddUI";
    }

    /** 재고 품목 수정 화면 */
    @GetMapping("/set")
    public String itemSet(Model model) {
        model.addAttribute("role", "본사");
        return "item/itemSetUI";
    }
}
