package com.rahul.currencyrate.service;

import com.rahul.currencyrate.model.ExchangeRate;
import com.rahul.currencyrate.repository.ExchangeRateRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class ExchangeRateProviderService {

    private final WebClient webClient;
    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateProviderService(ExchangeRateRepository exchangeRateRepository) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.frankfurter.app")
                .build();
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Scheduled(cron = "0 0 6 * * *") // runs every day at 6am
    public void syncRates() {
        fetchAndSave("EUR");
        fetchAndSave("USD");
        fetchAndSave("GBP");
    }

    public void fetchAndSave(String baseCurrency) {
        webClient.get()
                .uri("/latest?from=" + baseCurrency)
                .retrieve()
                .bodyToMono(Map.class)
                .subscribe(response -> {
                    Map<String, Object> rates = (Map<String, Object>) response.get("rates");
                    rates.forEach((target, rate) -> {
                        boolean exists = exchangeRateRepository
                                .findFirstByBaseCurrencyAndTargetCurrencyAndRateDateOrderByCreatedAtDesc(
                                        baseCurrency, target, LocalDate.now())
                                .isPresent();
                        if (!exists) {
                            ExchangeRate exchangeRate = new ExchangeRate(
                                    baseCurrency,
                                    target,
                                    new BigDecimal(rate.toString()),
                                    LocalDate.now()
                            );
                            exchangeRateRepository.save(exchangeRate);
                        }
                    });
                });
    }
}