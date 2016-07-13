package com.company.controller.exception;

import com.company.util.ModelName;
import com.company.util.View;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception exception) {
        return new ModelAndView(View.ERROR_PAGE, ModelName.ALL_PAGES_EXCEPTION, exception);
    }
}
