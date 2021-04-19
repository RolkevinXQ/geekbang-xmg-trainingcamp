package org.rolkevin.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

public class StringConverter implements Converter {
    @Override
    public String convert(String s) throws IllegalArgumentException, NullPointerException {
        return String.valueOf(s);
    }
}
