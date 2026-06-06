package com.rahul.currencyrate.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ConversionRequest(
    @NotBlank String sourceCurrency,
    @NotBlank String targetCurrency,
    @NotNull @DecimalMin(value = "0.01") BigDecimal amount
) {}
