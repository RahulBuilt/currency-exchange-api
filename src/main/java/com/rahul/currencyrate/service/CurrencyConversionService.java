package com.rahul.currencyrate.service;

import com.rahul.currencyrate.dto.ConversionRequest;
import com.rahul.currencyrate.dto.ConversionResponse;
import com.rahul.currencyrate.dto.ExchangeRateResponse;
import com.rahul.currencyrate.model.ConversionRecord;
import com.rahul.currencyrate.repository.ConversionRecordRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CurrencyConversionService {
    private final ExchangeRateService exchangeRateService;
    private final ConversionRecordRepository conversionRecordRepository;

    public CurrencyConversionService(ExchangeRateService exchangeRateService,
                                     ConversionRecordRepository conversionRecordRepository) {
        this.exchangeRateService = exchangeRateService;
        this.conversionRecordRepository = conversionRecordRepository;
    }

    public ConversionResponse convert(ConversionRequest request) {
        ExchangeRateResponse rate = exchangeRateService.getLatestRate(request.sourceCurrency(), request.targetCurrency());

        BigDecimal convertedAmount = request.amount()
            .multiply(rate.rate())
            .setScale(2, RoundingMode.HALF_UP);

        conversionRecordRepository.save(new ConversionRecord(
            rate.baseCurrency(), rate.targetCurrency(), request.amount(), convertedAmount, rate.rate()));

        return new ConversionResponse(
            rate.baseCurrency(), rate.targetCurrency(), request.amount(), convertedAmount, rate.rate(), rate.rateDate());
    }
}
