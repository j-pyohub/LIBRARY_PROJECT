package com.erp.controller;

import com.erp.dto.MenuDTO;
import com.erp.dto.MenuIngredientDTO;
import com.erp.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("menu")
    private String member(Model model){
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
