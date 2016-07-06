package com.company.controller;

import com.company.service.JokeService;
import com.company.service.UserService;
import com.company.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LogInController {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public ModelAndView authorization() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView signIn(@RequestParam(value="login") String login,
                               @RequestParam(value="password") String password,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        if(userService.isSignInOk(login, password)){
            Cookie cookie = new Cookie("jokerUser", login);
            cookie.setMaxAge(365*24*60*60);
            cookie.setPath("/");
            response.addCookie(cookie);

            Map<String, Object> model = new HashMap<>();
            model.put("jokes", jokeService.getAllJokes());
            return new ModelAndView("index", model);
        }
        else
            return new ModelAndView("login", "error", Message.SIGN_IN_ERROR);
    }

}
