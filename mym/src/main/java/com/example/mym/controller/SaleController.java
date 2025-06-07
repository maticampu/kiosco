package com.example.mym.controller;
import com.example.mym.dto.SaleDto;
import com.example.mym.service.SaleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    public SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public SaleDto saleCreated(@RequestBody SaleDto newSale) {
        return saleService.createSale(newSale);
    }

}
