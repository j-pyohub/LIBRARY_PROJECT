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

    // 나중에 실제 로직 붙일 때 사용 예정
    // private final ItemRepository itemRepository;

    /** 재고 품목 목록 조회 */
    @GetMapping("/list")
    public String itemList(Model model) {
        model.addAttribute("role", "본사");
        return "item/itemList";
    }

    /** 재고 품목 상세 조회 */
    @GetMapping("/detail")
    public String itemDetail(Model model) {
        model.addAttribute("role", "본사");
        return "item/itemDetail";
    }

    /** 재고 품목 등록 화면 */
    @GetMapping("/create")
    public String itemCreate(Model model) {
        model.addAttribute("role", "본사");
        return "item/itemCreate";
    }

    /** 재고 품목 수정 화면 */
    @GetMapping("/edit")
    public String itemEdit(Model model) {
        model.addAttribute("role", "본사");
        return "item/itemEdit";
    }
}
