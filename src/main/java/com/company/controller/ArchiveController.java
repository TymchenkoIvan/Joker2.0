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
@RequestMapping("/archive")
public class ArchiveController {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public ModelAndView listJokesArchive() {
        return new ModelAndView("archive", "jokes", jokeService.getArchivedJokes());
    }

    @RequestMapping("/recover")
    public ModelAndView recover(@RequestParam(value="jokeId") int jokeId,
                                @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<>();
        if(userService.isUserAdmin(login)) {
            jokeService.recoverJokeFromArchive(jokeId);
            model.put("jokes", jokeService.getArchivedJokes());
            return new ModelAndView("archive", model);
        }
        model.put("jokes", jokeService.getArchivedJokes());
        model.put("error", Message.IS_ADMIN_ERROR);
        return new ModelAndView("archive", model);
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(value="jokeId") int jokeId,
                               @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<>();
        if(userService.isUserAdmin(login)) {
            jokeService.deleteJoke(jokeId);
            model.put("jokes", jokeService.getArchivedJokes());
            return new ModelAndView("archive", model);
        }
        model.put("jokes", jokeService.getArchivedJokes());
        model.put("error", Message.IS_ADMIN_ERROR);
        return new ModelAndView("archive", model);
    }
}
