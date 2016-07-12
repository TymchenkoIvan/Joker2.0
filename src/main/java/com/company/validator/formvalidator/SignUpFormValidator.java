package com.company.validator.formvalidator;

import com.company.exception.JokerValidationException;
import com.company.service.UserService;
import com.company.util.Message;
import com.company.entity.bean.Bean;
import com.company.entity.bean.formbean.impl.SignUpForm;
import com.company.validator.BeanValidator;
import org.springframework.beans.factory.annotation.Autowired;

public class SignUpFormValidator extends FormValidator implements BeanValidator {

    @Autowired
    private UserService userService;

    @Override
    public void validate(Bean bean) throws JokerValidationException {
        SignUpForm form = (SignUpForm) bean;

        validateLogin(form.getLogin());
        validateMail(form.getMail());
        validatePassword(form.getPassword());
        validatePasswordConfirmation(form.getPassword(), form.getConfirmPassword());
    }

    private void validateLogin(String login) throws JokerValidationException {
        if(isNullOrEmpty(login) || !isValidField(login, LOGIN_PATTERN))
            throw new JokerValidationException(Message.LOGIN_ERROR);

        if(!userService.isLoginUnique(login))
            throw new JokerValidationException(Message.LOGIN_ERROR);
    }

    private void validateMail(String mail) throws JokerValidationException {
        if(isNullOrEmpty(mail) || !isValidField(mail, MAIL_PATTERN))
            throw new JokerValidationException(Message.MAIL_NOT_REAL);

        if(!userService.isMailUnique(mail))
            throw new JokerValidationException(Message.MAIL_NOT_UNIQUE);
    }

    private void validatePassword(String password) throws JokerValidationException {
        if(isNullOrEmpty(password) || !isValidField(password, PASSWORD_PATTERN))
            throw new JokerValidationException(Message.PASSWORD_ERROR);
    }

    private void validatePasswordConfirmation(String password, String confirm) throws JokerValidationException  {
        if(isNullOrEmpty(confirm) || password.equals(confirm))
            throw new JokerValidationException(Message.PASSWORD_ERROR);
    }
}
