package com.company.controller;

import com.company.service.JokeService;
import com.company.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "")
    public String addPage(Model model) {
        return View.ADD_JOKE_PAGE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView addAdv(@RequestParam(value="text") String text,
                               HttpServletRequest request,
                               HttpServletResponse response)
    {
        try {
            jokeService.addJoke(text);
            return new ModelAndView(View.INDEX_PAGE, "jokes", jokeService.getAllJokes());
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }
}
