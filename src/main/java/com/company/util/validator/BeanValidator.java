package com.company.util.validator;

import com.company.exception.JokerValidationException;
import com.company.util.bean.Bean;

public interface BeanValidator {

    String LOGIN_PATTERN = "login.pattern";
    String MAIL_PATTERN = "mail.pattern";
    String PASSWORD_PATTERN = "password.pattern";
    String JOKE_TEXT_PATTERN = "joke.text.pattern";

    void validate(Bean bean) throws JokerValidationException;
}
