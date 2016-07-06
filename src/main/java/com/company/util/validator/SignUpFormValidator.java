package com.company.util.validator;

import com.company.exception.JokerValidationException;
import com.company.service.UserService;
import com.company.util.Message;
import com.company.util.bean.Bean;
import com.company.util.bean.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource("classpath:property/validator.properties")
public class SignUpFormValidator extends Validator implements BeanValidator{

    //private String mailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Autowired
    private Environment env;

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
        if(isNullOrEmpty(login))
            throw new JokerValidationException(Message.LOGIN_ERROR);

        if(userService.isLoginUnique(login))
            throw new JokerValidationException(Message.LOGIN_ERROR);
    }

    private void validateMail(String mail) throws JokerValidationException {
        if(isNullOrEmpty(mail) || isMatchesToPattern(mail, env.getProperty("mail.pattern")))
            throw new JokerValidationException(Message.MAIL_NOT_REAL);

        if(userService.isMailUnique(mail))
            throw new JokerValidationException(Message.MAIL_NOT_UNIQUE);
    }

    private void validatePassword(String password) throws JokerValidationException {
        if(isNullOrEmpty(password))
            throw new JokerValidationException(Message.PASSWORD_ERROR);
    }

    private void validatePasswordConfirmation(String password, String confirm) throws JokerValidationException  {
        if(isNullOrEmpty(confirm) || password.equals(confirm))
            throw new JokerValidationException(Message.PASSWORD_ERROR);
    }
}
