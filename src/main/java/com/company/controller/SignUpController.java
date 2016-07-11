package com.company.controller;

import com.company.entity.bean.formbean.impl.SignUpForm;
import com.company.exception.JokerValidationException;
import com.company.service.UserService;
import com.company.entity.bean.formbean.FormBeans;
import com.company.util.View;
import com.company.util.ModelName;
import com.company.populator.formbean.FormBeanFactory;
import com.company.validator.formvalidator.SignUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private FormBeanFactory formBeanFactory;

    @Autowired
    private SignUpFormValidator formValidator;

    @RequestMapping("")
    public ModelAndView signUpPage() {
        return new ModelAndView(View.SIGN_UP_PAGE);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView createUser(@RequestParam(value="login") String login,
                                @RequestParam(value="mail") String mail,
                                @RequestParam(value="telephone") String telephone,
                                @RequestParam(value="password") String password,
                                @RequestParam(value="confirm") String confirm,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            SignUpForm formBean = (SignUpForm) formBeanFactory.create(FormBeans.SIGN_UP, request);
            formValidator.validate(formBean);
            userService.createUser(formBean);
        } catch (JokerValidationException e) {
            e.printStackTrace();
            return new ModelAndView(View.SIGN_UP_PAGE, ModelName.ALL_PAGES_ERROR_MESSAGE, e.getMessage());
        }
        return new ModelAndView(View.INDEX_PAGE);
    }
}
