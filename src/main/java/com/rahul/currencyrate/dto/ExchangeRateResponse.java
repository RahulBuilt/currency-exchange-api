package com.rahul.currencyrate.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ExchangeRateResponse(
    String baseCurrency,
    String targetCurrency,
    BigDecimal rate,
    LocalDate rateDate
) implements Serializable {}
