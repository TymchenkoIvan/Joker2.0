package com.company.controller;

import com.company.entity.bean.formbean.FormBeans;
import com.company.entity.bean.formbean.impl.LogInForm;
import com.company.exception.JokerValidationException;
import com.company.populator.factory.FormBeanFactory;
import com.company.service.JokeService;
import com.company.util.Message;
import com.company.util.ModelName;
import com.company.util.View;
import com.company.validator.formvalidator.LogInFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LogInController {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private FormBeanFactory formBeanFactory;

    @Autowired
    private LogInFormValidator formValidator;

    @RequestMapping("")
    public ModelAndView logInPage() {
        return new ModelAndView(View.LOG_IN_PAGE);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView logIn(@RequestParam(value="login") String login,
                               @RequestParam(value="password") String password,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        try {
            LogInForm formBean = (LogInForm) formBeanFactory.create(FormBeans.LOG_IN, request);
            formValidator.validate(formBean);

            Cookie cookie = new Cookie("jokerUser", login);
            cookie.setMaxAge(365*24*60*60);
            cookie.setPath("/");
            response.addCookie(cookie);

            return new ModelAndView("redirect:/");
        } catch (JokerValidationException e) {
            e.printStackTrace();
            return new ModelAndView(View.LOG_IN_PAGE, ModelName.ALL_PAGES_ERROR_MESSAGE, Message.LOG_IN_ERROR);
        }
    }
}
