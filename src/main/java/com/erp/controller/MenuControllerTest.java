package com.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuControllerTest {

    @GetMapping("/menu")
    public String menu() {
        return "menuUI";
    }
}
