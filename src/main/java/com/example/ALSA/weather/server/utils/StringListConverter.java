package com.example.ALSA.weather.server.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import static java.util.Collections.emptyList;

/**
 * List<String> as JPA instance variable (persist List<String)
 * --> Helper for ORM (Object Relational Mapping)
 * --> Convert List instance variable to String in associated DB table.
 * --> Format in DB: data2;data2;data3 [...]
 */

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        //return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
        // Below requires that DB column accepts null
        return stringList != null ? String.join(SPLIT_CHAR, stringList) : null;
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        return string != null ? Arrays.asList(string.split(SPLIT_CHAR)) : emptyList();
    }
}
