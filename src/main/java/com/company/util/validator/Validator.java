package com.company.util.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    @Autowired
    protected Environment properties;

    protected boolean isValidField(String field, String propertyName) {
        return !properties.containsProperty(propertyName) || isMatchesToPattern(field, properties.getProperty(propertyName));
    }

    public boolean isNullOrEmpty(String line){
        return line == null || line.trim().isEmpty();
    }

    public boolean isMatchesToPattern(String matcher, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(matcher);
        return m.matches();
    }
}
