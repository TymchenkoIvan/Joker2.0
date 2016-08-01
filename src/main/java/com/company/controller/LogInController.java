package com.company.controller;

import com.company.entity.bean.formbean.impl.LogInForm;
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
@RequestMapping("/login")
public class LogInController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String logInPage(Map<String, Object> model) {
        model.put(Model.LOG_IN_FORM, new LogInForm());
        return View.LOG_IN_PAGE;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView logIn(@Valid LogInForm logInForm,
                              BindingResult bindingResult,
                              HttpSession session) {
        if(bindingResult.hasErrors())
            return new ModelAndView(View.LOG_IN_PAGE);

        return new ModelAndView(View.REDIRECT + View.INDEX_PAGE);
    }
}
