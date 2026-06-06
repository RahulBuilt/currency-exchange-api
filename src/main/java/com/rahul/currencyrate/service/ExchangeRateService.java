package com.rahul.currencyrate.service;

import com.rahul.currencyrate.dto.ExchangeRateResponse;
import com.rahul.currencyrate.exception.ExchangeRateNotFoundException;
import com.rahul.currencyrate.model.ExchangeRate;
import com.rahul.currencyrate.repository.ExchangeRateRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Cacheable(value = "exchangeRates", key = "#baseCurrency.toUpperCase() + '-' + #targetCurrency.toUpperCase()")
    public ExchangeRateResponse getLatestRate(String baseCurrency, String targetCurrency) {
        String normalizedBase = normalizeCurrency(baseCurrency);
        String normalizedTarget = normalizeCurrency(targetCurrency);

        ExchangeRate rate = exchangeRateRepository
            .findFirstByBaseCurrencyAndTargetCurrencyAndRateDateOrderByCreatedAtDesc(
                normalizedBase, normalizedTarget, LocalDate.now())
            .orElseGet(() -> createDemoRate(normalizedBase, normalizedTarget));

        return new ExchangeRateResponse(rate.getBaseCurrency(), rate.getTargetCurrency(), rate.getRate(), rate.getRateDate());
    }

    private ExchangeRate createDemoRate(String baseCurrency, String targetCurrency) {
        BigDecimal demoRate = switch (baseCurrency + "_" + targetCurrency) {
            case "EUR_USD" -> new BigDecimal("1.0900");
            case "USD_EUR" -> new BigDecimal("0.9174");
            case "EUR_EGP" -> new BigDecimal("52.8000");
            case "EGP_EUR" -> new BigDecimal("0.0189");
            case "USD_EGP" -> new BigDecimal("48.5000");
            case "EGP_USD" -> new BigDecimal("0.0206");
            default -> throw new ExchangeRateNotFoundException(
                "No demo rate configured for " + baseCurrency + " to " + targetCurrency);
        };
        return exchangeRateRepository.save(new ExchangeRate(baseCurrency, targetCurrency, demoRate, LocalDate.now()));
    }

    private String normalizeCurrency(String currency) {
        return currency.trim().toUpperCase();
    }
}
