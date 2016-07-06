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
@RequestMapping("/archive")
public class ArchiveController {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public ModelAndView archivePage() {
        return new ModelAndView(View.ARCHIVE_JOKES_PAGE, ModelName.ARCHIVE_PAGE_JOKE_LIST, jokeService.getArchivedJokes());
    }

    @RequestMapping("/recover")
    public ModelAndView recoverJoke(@RequestParam(value="jokeId") int jokeId,
                                    @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<>();

        if(userService.isUserAdmin(login)) {
            jokeService.recoverJokeFromArchive(jokeId);
            model.put(ModelName.ARCHIVE_PAGE_JOKE_LIST, jokeService.getArchivedJokes());
            return new ModelAndView(View.ARCHIVE_JOKES_PAGE, model);
        }

        model.put(ModelName.ARCHIVE_PAGE_JOKE_LIST, jokeService.getArchivedJokes());
        model.put(ModelName.ALL_PAGES_ERROR_MESSAGE, Message.IS_ADMIN_ERROR);
        return new ModelAndView(View.ARCHIVE_JOKES_PAGE, model);
    }

    @RequestMapping("/delete")
    public ModelAndView deleteJoke(@RequestParam(value="jokeId") int jokeId,
                                   @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<>();

        if(userService.isUserAdmin(login)) {
            jokeService.deleteJoke(jokeId);
            model.put(ModelName.ARCHIVE_PAGE_JOKE_LIST, jokeService.getArchivedJokes());
            return new ModelAndView(View.ARCHIVE_JOKES_PAGE, model);
        }

        model.put(ModelName.ARCHIVE_PAGE_JOKE_LIST, jokeService.getArchivedJokes());
        model.put(ModelName.ALL_PAGES_ERROR_MESSAGE, Message.IS_ADMIN_ERROR);
        return new ModelAndView(View.ARCHIVE_JOKES_PAGE, model);
    }
}
