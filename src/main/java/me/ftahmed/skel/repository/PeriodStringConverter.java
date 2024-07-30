package me.ftahmed.skel.repository;

import java.time.Period;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PeriodStringConverter
        implements AttributeConverter<Period, String> {

    @Override
    public String convertToDatabaseColumn(Period attribute) {
        return attribute.toString();
    }

    @Override
    public Period convertToEntityAttribute(String dbData) {
        return Period.parse(dbData);
    }
}