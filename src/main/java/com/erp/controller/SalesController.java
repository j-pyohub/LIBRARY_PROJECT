package com.erp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SalesController {


    @GetMapping("salesMain")
    private String salesMain(){ return "sales/salesMainUI"; }
}
