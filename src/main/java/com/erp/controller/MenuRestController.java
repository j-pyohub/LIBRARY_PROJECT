package com.erp.controller;

import com.erp.dto.MenuDTO;
import com.erp.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuRestController {
    private final MenuService menuService;

    @GetMapping("menuList")
    public List<MenuDTO> getAllMenu(){
        return menuService.getAllMenu();
    }
}
