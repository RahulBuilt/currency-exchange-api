package com.rahul.currencyrate.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "exchange_rates",
       uniqueConstraints = @UniqueConstraint(columnNames = {"baseCurrency", "targetCurrency", "rateDate"}))
public class ExchangeRate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String baseCurrency;
    private String targetCurrency;
    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal rate;
    private LocalDate rateDate;
    private OffsetDateTime createdAt = OffsetDateTime.now();

    protected ExchangeRate() {}

    public ExchangeRate(String baseCurrency, String targetCurrency, BigDecimal rate, LocalDate rateDate) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
        this.rateDate = rateDate;
    }

    public Long getId() { return id; }
    public String getBaseCurrency() { return baseCurrency; }
    public String getTargetCurrency() { return targetCurrency; }
    public BigDecimal getRate() { return rate; }
    public LocalDate getRateDate() { return rateDate; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
}
