package com.rahul.currencyrate.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ConversionResponse(
    String sourceCurrency,
    String targetCurrency,
    BigDecimal sourceAmount,
    BigDecimal convertedAmount,
    BigDecimal rate,
    LocalDate rateDate
) implements Serializable {}