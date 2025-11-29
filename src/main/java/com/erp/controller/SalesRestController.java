package com.erp.controller;

import com.erp.dto.KPIDTO;
import com.erp.dto.MenuRatioDTO;
import com.erp.dto.SalesChartDTO;
import com.erp.dto.TotalStoreSalesDTO;
import com.erp.service.SalesChartService;
import com.erp.service.SalesKPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalesRestController {

    private final SalesChartService salesChartService;
    private final SalesKPIService salesKPIService;

    @GetMapping("/KPI")
    public KPIDTO getKPI(
            @RequestParam String type,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return salesKPIService.getKPIByDate(type, startDate, endDate);
    }

    @GetMapping("salesChart")
    public SalesChartDTO getSalesChart(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String type

    ) {
        return salesChartService.getSalesChartByDate(startDate,endDate,type);
    }

    @GetMapping("/totalStoreSales")
    public List<TotalStoreSalesDTO> getTop5StoreSales() {
        return salesChartService.getTotalStoreSales();
    }

    @GetMapping("/menuRatio")
    public List<MenuRatioDTO> getMenuRatio() {
        return salesChartService.getMenuRatio();
    }
}
