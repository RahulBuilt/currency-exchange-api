package com.rahul.currencyrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CurrencyRateApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyRateApplication.class, args);
    }
}
