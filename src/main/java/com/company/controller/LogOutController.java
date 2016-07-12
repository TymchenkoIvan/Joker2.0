package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogOutController {

    @RequestMapping("")
    public ModelAndView logOut(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }
}
