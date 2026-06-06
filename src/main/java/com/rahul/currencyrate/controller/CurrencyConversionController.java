package com.rahul.currencyrate.controller;

import com.rahul.currencyrate.dto.ConversionRequest;
import com.rahul.currencyrate.dto.ConversionResponse;
import com.rahul.currencyrate.service.CurrencyConversionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/conversions")
public class CurrencyConversionController {
    private final CurrencyConversionService currencyConversionService;

    public CurrencyConversionController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    @PostMapping
    public ConversionResponse convert(@Valid @RequestBody ConversionRequest request) {
        return currencyConversionService.convert(request);
    }
}
