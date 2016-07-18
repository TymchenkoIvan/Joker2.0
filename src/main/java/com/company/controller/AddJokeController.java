package com.company.controller;

import com.company.entity.bean.dtobean.impl.UserDTO;
import com.company.entity.bean.formbean.FormBeans;
import com.company.entity.bean.formbean.impl.AddJokeForm;
import com.company.exception.JokerValidationException;
import com.company.populator.factory.FormBeanFactory;
import com.company.service.JokeService;
import com.company.util.ModelName;
import com.company.util.View;
import com.company.validator.formvalidator.AddJokeFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/add")
public class AddJokeController {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private FormBeanFactory formBeanFactory;

    @Autowired
    private AddJokeFormValidator formValidator;

    @RequestMapping(value = "")
    public String addJokePage(HttpSession session) {
        return session.getAttribute("user") != null
                ? View.ADD_JOKE_PAGE
                : "redirect:/login";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createNewJoke(HttpSession session,
                                      HttpServletRequest request)
    {
        int userId = ((UserDTO) session.getAttribute("user")).getId();

        try {
            AddJokeForm formBean = (AddJokeForm) formBeanFactory.create(FormBeans.ADD_JOKE, request);
            formValidator.validate(formBean);
            jokeService.addJoke(formBean, userId);

            return new ModelAndView("redirect:/");
        } catch (JokerValidationException e) {
            return new ModelAndView(View.ADD_JOKE_PAGE, ModelName.ALL_PAGES_ERROR_MESSAGE, e.getMessage());
        }
    }
}
