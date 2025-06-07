package com.example.mym.controller;

import com.example.mym.dto.SaleSummaryDto;
import com.example.mym.dto.SaleSummaryWithConditionsDto;
import com.example.mym.service.SaleSummaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salesummary")
public class SaleSummaryController {

    private final SaleSummaryService saleSummaryService;

    public SaleSummaryController(SaleSummaryService saleSummaryService) {
        this.saleSummaryService = saleSummaryService;
    }

    @GetMapping
    public SaleSummaryDto getDailySaleSummary(){
        return saleSummaryService.getDailySaleSummary();
    }

    @GetMapping("/conditions")
    public SaleSummaryDto getSummaryWithConditions(@RequestBody SaleSummaryWithConditionsDto saleConditions){
        return saleSummaryService.getSaleSummaryWithConditions(saleConditions);
    }
}
