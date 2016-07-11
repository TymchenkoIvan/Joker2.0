package com.company.validator.formvalidator;

import com.company.exception.JokerValidationException;
import com.company.util.Message;
import com.company.entity.bean.formbean.impl.AddJokeForm;
import com.company.entity.bean.Bean;
import com.company.validator.BeanValidator;

public class AddJokeFormValidator extends FormValidator implements BeanValidator {

    @Override
    public void validate(Bean bean) throws JokerValidationException {
        AddJokeForm form = (AddJokeForm) bean;

        validateText(form.getText());
    }

    private void validateText(String text) throws JokerValidationException {
        if(isNullOrEmpty(text) || !isValidField(text, JOKE_TEXT_PATTERN))
            throw new JokerValidationException(Message.ADD_JOKE_ERROR);
    }
}
