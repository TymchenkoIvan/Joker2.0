package com.company.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean isNullOrEmpty(String line){
        return line == null || line.trim().isEmpty();
    }

    public boolean isMatchesToPattern(String matcher, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(matcher);
        return m.matches();
    }
}
