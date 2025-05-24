package com.example.pricecomparatorapp.Utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter extends AbstractBeanField<LocalDate, String> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected LocalDate convert(String value) throws CsvDataTypeMismatchException {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
            throw new CsvDataTypeMismatchException(value, LocalDate.class,
                    "Failed to parse date. Expected format: yyyy-MM-dd");
        }
    }
}