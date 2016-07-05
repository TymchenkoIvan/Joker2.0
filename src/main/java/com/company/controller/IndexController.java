package com.company.controller;

import com.company.service.JokeService;
import com.company.service.UserService;
import com.company.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class IndexController {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private UserService userService;

    @RequestMapping(value={"", " * "})
    public ModelAndView mainPage() {
        return new ModelAndView("index", "jokes", jokeService.getAllJokes());
    }

    @RequestMapping("/like")
    public ModelAndView like(@RequestParam(value="jokeId") int jokeId,
                             @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<>();
        if(userService.isCorrectAction(jokeId, login)){
            jokeService.addLike(jokeId);
            model.put("jokes", jokeService.getAllJokes());
            return new ModelAndView("index", model);
        }
        model.put("jokes", jokeService.getAllJokes());
        model.put("error", Message.VOTE_ERROR);
        return new ModelAndView("index", model);
    }

    @RequestMapping("/dislike")
     public ModelAndView dislike(@RequestParam(value="jokeId") int jokeId,
                                 @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<>();
        if(userService.isCorrectAction(jokeId, login)) {
            jokeService.addDislike(jokeId);
            model.put("jokes", jokeService.getAllJokes());
            return new ModelAndView("index", model);
        }
        model.put("jokes", jokeService.getAllJokes());
        model.put("error", Message.VOTE_ERROR);
        return new ModelAndView("index", model);
    }
}