package com.company.controller;

import com.company.entity.bean.formbean.impl.LogInForm;
import com.company.service.UserService;
import com.company.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LogInController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String logInPage(Map<String, Object> model, HttpSession session) {
        if(session.getAttribute("user") != null)
            return "redirect:/logout";

        model.put("logInForm", new LogInForm());
        return View.LOG_IN_PAGE;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView logIn(@Valid @ModelAttribute("logInForm") LogInForm logInForm,
                                      BindingResult bindingResult,
                                      HttpSession session) {
        if(bindingResult.hasErrors() || !userService.isLoginPairCorrect(logInForm.getLogin(), logInForm.getPassword()))
            return new ModelAndView(View.LOG_IN_PAGE);

        session.setAttribute("user", userService.getUserByLogin(logInForm.getLogin()));
        return new ModelAndView("redirect:/");
    }
}
