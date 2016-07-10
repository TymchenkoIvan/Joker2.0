package com.company.controller;

import com.company.exception.JokerValidationException;
import com.company.service.JokeService;
import com.company.entity.bean.formbean.FormBeans;
import com.company.util.Message;
import com.company.util.ModelName;
import com.company.util.View;
import com.company.entity.bean.formbean.LogInForm;
import com.company.populator.BeanFactory;
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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LogInController {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private BeanFactory beanFactory;

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
            LogInForm formBean = (LogInForm) beanFactory.create(FormBeans.LOG_IN, request);
            formValidator.validate(formBean);

            Cookie cookie = new Cookie("jokerUser", login);
            cookie.setMaxAge(365*24*60*60);
            cookie.setPath("/");
            response.addCookie(cookie);

            Map<String, Object> model = new HashMap<>();
            model.put(ModelName.INDEX_PAGE_JOKE_LIST, jokeService.getAllJokes());

            return new ModelAndView(View.INDEX_PAGE, model);
        } catch (JokerValidationException e) {
            e.printStackTrace();
            return new ModelAndView(View.LOG_IN_PAGE, ModelName.ALL_PAGES_ERROR_MESSAGE, Message.LOG_IN_ERROR);
        }
    }
}
