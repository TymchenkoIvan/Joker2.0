package com.company.controller;

import com.company.entity.bean.dtobean.impl.UserDTO;
import com.company.entity.bean.formbean.impl.JokeForm;
import com.company.service.JokeService;
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
@RequestMapping("/jokeForm")
public class JokeFormController {

    @Autowired
    private JokeService jokeService;

    @RequestMapping(method = RequestMethod.GET)
    public String jokeFormPage(Map<String, Object> model, HttpSession session) {
        if(session.getAttribute(Model.SESSION_USER) == null)
            return View.REDIRECT + View.LOG_IN_PAGE;

        model.put(Model.JOKE_FORM, new JokeForm());
        return View.JOKE_FORM_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView jokeForm(@Valid JokeForm jokeForm,
                                 BindingResult bindingResult,
                                 HttpSession session) {
        if(bindingResult.hasErrors())
            return new ModelAndView(View.JOKE_FORM_PAGE);

        int userId = ((UserDTO) session.getAttribute(Model.SESSION_USER)).getId();
        jokeService.addJoke(jokeForm, userId);
        return new ModelAndView(View.REDIRECT + View.INDEX_PAGE);
    }
}
