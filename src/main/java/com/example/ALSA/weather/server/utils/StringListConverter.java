package com.example.ALSA.weather.server.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
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
    // model --> DB
    public String convertToDatabaseColumn(List<String> stringList) {
        //return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
        // Below requires that DB column accepts null
        if(stringList != null) {
            if(!stringList.isEmpty()) {
                return String.join(SPLIT_CHAR, stringList);
            }
        }
        return null;
    }

    @Override
    // DB --> model
    public List<String> convertToEntityAttribute(String string) {
        //return string != null || string.trim().length() == 0 ? Arrays.asList(string.split(SPLIT_CHAR)) : emptyList();

        if(string != null) {
            if(string.trim().length() > 0) {
                return Arrays.asList(string.split(SPLIT_CHAR));
            }
        }
        return new ArrayList<String>();
    }
}
