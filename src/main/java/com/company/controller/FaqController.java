package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/faq")
public class FaqController {

    @RequestMapping("")
    public ModelAndView faq() {
        return new ModelAndView("faq");
    }
}
