package com.study.board.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanConverter implements AttributeConverter<Boolean, String> {

    /**
     * boolean을 DB에 저장할 'YN'으로 변환
     * @param b
     * @return
     */
    @Override
    public String convertToDatabaseColumn(Boolean b) {
        return (b != null && b.equals(true)) ? "Y" : "N";
    }

    /**
     * boolean값을 Entity boolean으로 변환
     * @param s
     * @return
     */
    @Override
    public Boolean convertToEntityAttribute(String s) {
        return (s != null && s.equals("Y")) ? true : false;
    }
}