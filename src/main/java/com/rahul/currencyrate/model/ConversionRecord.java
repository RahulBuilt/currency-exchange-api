package com.rahul.currencyrate.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "conversion_records")
public class ConversionRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sourceCurrency;
    private String targetCurrency;
    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal sourceAmount;
    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal convertedAmount;
    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal rate;
    private OffsetDateTime convertedAt = OffsetDateTime.now();

    protected ConversionRecord() {}

    public ConversionRecord(String sourceCurrency, String targetCurrency, BigDecimal sourceAmount,
                            BigDecimal convertedAmount, BigDecimal rate) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.sourceAmount = sourceAmount;
        this.convertedAmount = convertedAmount;
        this.rate = rate;
    }

    public Long getId() { return id; }
    public String getSourceCurrency() { return sourceCurrency; }
    public String getTargetCurrency() { return targetCurrency; }
    public BigDecimal getSourceAmount() { return sourceAmount; }
    public BigDecimal getConvertedAmount() { return convertedAmount; }
    public BigDecimal getRate() { return rate; }
    public OffsetDateTime getConvertedAt() { return convertedAt; }
}
