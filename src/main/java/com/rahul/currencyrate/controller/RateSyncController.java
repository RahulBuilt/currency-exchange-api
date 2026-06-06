package com.rahul.currencyrate.controller;

import com.rahul.currencyrate.service.ExchangeRateProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rates")
public class RateSyncController {

    private final ExchangeRateProviderService providerService;

    public RateSyncController(ExchangeRateProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncRates() {
        providerService.syncNow();
        return ResponseEntity.ok("Rate sync triggered successfully");
    }
}