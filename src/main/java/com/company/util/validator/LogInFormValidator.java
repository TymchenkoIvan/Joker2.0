package com.company.util.validator;

import com.company.exception.JokerValidationException;
import com.company.service.UserService;
import com.company.util.Message;
import com.company.util.bean.Bean;
import com.company.util.bean.LogInForm;
import org.springframework.beans.factory.annotation.Autowired;

public class LogInFormValidator extends Validator implements BeanValidator{

    @Autowired
    private UserService userService;

    @Override
    public void validate(Bean bean) throws JokerValidationException {
        LogInForm form = (LogInForm) bean;

        validateLogin(form.getLogin());
        validatePassword(form.getPassword());
        validatePair(form.getLogin(), form.getPassword());
    }

    private void validateLogin(String login) throws JokerValidationException {
        if(isNullOrEmpty(login))
            throw new JokerValidationException(Message.LOG_IN_ERROR);
    }

    private void validatePassword(String password) throws JokerValidationException {
        if(isNullOrEmpty(password))
            throw new JokerValidationException(Message.LOG_IN_ERROR);
    }

    private void validatePair(String login, String password) throws JokerValidationException {
        if(!userService.isLoginPairCorrect(login, password))
            throw new JokerValidationException(Message.LOG_IN_ERROR);
    }
}
