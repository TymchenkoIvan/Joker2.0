package com.company.controller;

import com.company.entity.bean.dtobean.impl.UserDTO;
import com.company.service.JokeService;
import com.company.service.VoteService;
import com.company.util.Message;
import com.company.util.Model;
import com.company.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class IndexController {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private VoteService voteService;

    @RequestMapping(value={"", " * "})
    public ModelAndView indexPage() {
        return new ModelAndView(View.INDEX_PAGE, Model.INDEX_PAGE_JOKE_LIST, jokeService.getNewJokes());
    }

    @RequestMapping("{jokeId}/like")
    public ModelAndView likeAction(@PathVariable("jokeId") int jokeId, HttpSession session) {
        int userId = ((UserDTO) session.getAttribute(Model.SESSION_USER)).getId();

        if(voteService.isVotePossible(jokeId, userId)){
            voteService.addLike(jokeId, userId);
            return new ModelAndView(View.REDIRECT + View.INDEX_PAGE);
        }
        return getModelAndView(Message.VOTE_ERROR);
    }

    @RequestMapping("{jokeId}/dislike")
    public ModelAndView dislikeAction(@PathVariable("jokeId") int jokeId, HttpSession session) {
        int userId = ((UserDTO) session.getAttribute(Model.SESSION_USER)).getId();

        if(voteService.isVotePossible(jokeId, userId)){
            voteService.addDislike(jokeId, userId);
            return new ModelAndView(View.REDIRECT + View.INDEX_PAGE);
        }
        return getModelAndView(Message.VOTE_ERROR);
    }


    private ModelAndView getModelAndView(String errorMessage) {
        Map<String, Object> model = new HashMap<>();
        model.put(Model.INDEX_PAGE_JOKE_LIST, jokeService.getNewJokes());
        model.put(Model.ALL_PAGES_ERROR_MESSAGE, errorMessage);

        return new ModelAndView(View.INDEX_PAGE, model);
    }
}