package com.company.controller;

import com.company.service.JokeService;
import com.company.service.UserService;
import com.company.util.Message;
import com.company.util.ModelName;
import com.company.util.View;
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
    public ModelAndView indexPage() {
        return new ModelAndView(View.INDEX_PAGE, ModelName.INDEX_PAGE_JOKE_LIST, jokeService.getAllJokes());
    }

    @RequestMapping("/like")
    public ModelAndView likeAction(@RequestParam(value="jokeId") int jokeId,
                                   @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<>();

        if(userService.isCorrectAction(jokeId, login)){
            jokeService.addLike(jokeId);
            model.put(ModelName.INDEX_PAGE_JOKE_LIST, jokeService.getAllJokes());
            return new ModelAndView(View.INDEX_PAGE, model);
        }

        model.put(ModelName.INDEX_PAGE_JOKE_LIST, jokeService.getAllJokes());
        model.put(ModelName.ALL_PAGES_ERROR_MESSAGE, Message.VOTE_ERROR);
        return new ModelAndView(View.INDEX_PAGE, model);
    }

    @RequestMapping("/dislike")
     public ModelAndView dislikeAction(@RequestParam(value="jokeId") int jokeId,
                                       @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<>();

        if(userService.isCorrectAction(jokeId, login)) {
            jokeService.addDislike(jokeId);
            model.put(ModelName.INDEX_PAGE_JOKE_LIST, jokeService.getAllJokes());
            return new ModelAndView(View.INDEX_PAGE, model);
        }

        model.put(ModelName.INDEX_PAGE_JOKE_LIST, jokeService.getAllJokes());
        model.put(ModelName.ALL_PAGES_ERROR_MESSAGE, Message.VOTE_ERROR);
        return new ModelAndView(View.INDEX_PAGE, model);
    }
}