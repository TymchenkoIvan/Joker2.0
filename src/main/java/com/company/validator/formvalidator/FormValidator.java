package com.company.validator.formvalidator;

import com.company.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public abstract class FormValidator extends Validator {

    @Autowired
    protected Environment properties;

    protected static final String LOGIN_PATTERN = "login.pattern";
    protected static final String MAIL_PATTERN = "mail.pattern";
    protected static final String PASSWORD_PATTERN = "password.pattern";
    protected static final String JOKE_TEXT_PATTERN = "joke.text.pattern";

    protected boolean isValidField(String field, String propertyName) {
        return !properties.containsProperty(propertyName) || isMatchesToPattern(field, properties.getProperty(propertyName));
    }
}
