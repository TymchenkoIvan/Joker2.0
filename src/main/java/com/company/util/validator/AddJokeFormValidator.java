package com.company.util.validator;

import com.company.exception.JokerValidationException;
import com.company.util.Message;
import com.company.util.bean.AddJokeForm;
import com.company.util.bean.Bean;

public class AddJokeFormValidator extends Validator implements BeanValidator{

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
