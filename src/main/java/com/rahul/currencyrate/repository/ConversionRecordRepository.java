package com.rahul.currencyrate.repository;

import com.rahul.currencyrate.model.ConversionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionRecordRepository extends JpaRepository<ConversionRecord, Long> {}
