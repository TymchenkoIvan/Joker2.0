package com.company.controller;

import com.company.entity.bean.formbean.impl.SignUpForm;
import com.company.service.UserService;
import com.company.util.Model;
import com.company.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String signUpPage(Map<String, Object> model, HttpSession session) {
        if(session.getAttribute(Model.SESSION_USER) != null)
            return View.REDIRECT + View.LOG_OUT_PAGE;

        model.put(Model.SIGN_UP_FORM, new SignUpForm());
        return View.SIGN_UP_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView signUp(@Valid SignUpForm signUpForm,
                               BindingResult bindingResult,
                               HttpSession session) {
        if(bindingResult.hasErrors() || !userService.isSignUpInfoCorrect(signUpForm))
            return new ModelAndView(View.SIGN_UP_PAGE);

        userService.createUser(signUpForm);
        session.setAttribute(Model.SESSION_USER, userService.getUserByLogin(signUpForm.getLogin()));
        return new ModelAndView(View.REDIRECT + View.INDEX_PAGE);
    }
}
