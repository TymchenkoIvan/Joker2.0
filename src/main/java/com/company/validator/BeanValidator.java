package com.company.validator;

import com.company.exception.JokerValidationException;
import com.company.entity.bean.Bean;

public interface BeanValidator {

    void validate(Bean bean) throws JokerValidationException;
}
