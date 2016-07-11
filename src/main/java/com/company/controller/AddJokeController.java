package com.company.controller;

import com.company.entity.Joke;
import com.company.entity.bean.formbean.FormBeans;
import com.company.entity.bean.formbean.impl.AddJokeForm;
import com.company.exception.JokerValidationException;
import com.company.populator.formbean.FormBeanFactory;
import com.company.service.JokeService;
import com.company.util.ModelName;
import com.company.util.View;
import com.company.validator.formvalidator.AddJokeFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String addJokePage() {
        return View.ADD_JOKE_PAGE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createNewJoke(@RequestParam(value = "text") String text,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
    {
        try {
            AddJokeForm formBean = (AddJokeForm) formBeanFactory.create(FormBeans.ADD_JOKE, request);
            formValidator.validate(formBean);

            Joke joke = new Joke();
            joke.setText(text);
            jokeService.addJoke(joke);

            return new ModelAndView(View.INDEX_PAGE, ModelName.INDEX_PAGE_JOKE_LIST, jokeService.getAllJokes());
        } catch (JokerValidationException e) {
            return new ModelAndView(View.ADD_JOKE_PAGE, ModelName.ALL_PAGES_ERROR_MESSAGE, e.getMessage());
        }
    }
}
