package com.erp.controller;

import com.erp.dto.MenuDTO;
import com.erp.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping("menu")
    private String member(){
        return "menu/menuUI";
    }

    @GetMapping("menuDetail")
    public String menuDetail(@RequestParam Long menuNo, Model model) {

        MenuDTO menu = menuService.getMenuDetail(menuNo);

        model.addAttribute("menu", menu);
        model.addAttribute("sizeList", menu.getSizeList());
        model.addAttribute("ingredients", menu.getIngredients());
        model.addAttribute("hasSize", menu.isHasSize());

        return "menu/menuDetailUI";
    }
}
