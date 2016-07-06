package com.company.util.validator;

import com.company.exception.JokerValidationException;
import com.company.util.bean.Bean;

public interface BeanValidator {

    void validate(Bean bean) throws JokerValidationException;
}
