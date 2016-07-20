package com.company.controller;

import com.company.entity.bean.dtobean.impl.UserDTO;
import com.company.entity.bean.formbean.impl.JokeForm;
import com.company.service.JokeService;
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
@RequestMapping("/jokeForm")
public class JokeFormController {

    @Autowired
    private JokeService jokeService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewLogin(Map<String, Object> model, HttpSession session) {
        if(session.getAttribute("user") == null)
            return "redirect:/login";

        model.put("jokeForm", new JokeForm());
        return View.JOKE_FORM_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createNewJoke(@Valid @ModelAttribute("jokeForm") JokeForm jokeForm,
                                      BindingResult bindingResult,
                                      HttpSession session)
    {
        if(bindingResult.hasErrors())
            return new ModelAndView(View.JOKE_FORM_PAGE);

        int userId = ((UserDTO) session.getAttribute("user")).getId();
        jokeService.addJoke(jokeForm, userId);
        return new ModelAndView("redirect:/");
    }
}
