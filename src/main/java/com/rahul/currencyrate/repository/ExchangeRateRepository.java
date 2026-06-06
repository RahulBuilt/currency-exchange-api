package com.rahul.currencyrate.repository;

import com.rahul.currencyrate.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findFirstByBaseCurrencyAndTargetCurrencyAndRateDateOrderByCreatedAtDesc(
        String baseCurrency, String targetCurrency, LocalDate rateDate);
}
