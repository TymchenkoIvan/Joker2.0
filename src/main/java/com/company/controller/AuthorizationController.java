package com.company.controller;


import com.company.entity.User;
import com.company.service.UserService;
import com.company.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public ModelAndView authorization() {
        return new ModelAndView("authorization");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam(value="login") String login,
                                @RequestParam(value="mail") String mail,
                                @RequestParam(value="telephone") String telephone,
                                @RequestParam(value="password") String password,
                                @RequestParam(value="repeat_password") String repeat_password,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            if(!userService.isLoginUnique(login))
                return new ModelAndView("authorization", "error", Message.LOGIN_ERROR);
            if(!userService.isMailReal(mail))
                return new ModelAndView("authorization", "error", Message.MAIL_NOT_REAL);
            if(!userService.isMailUnique(mail))
                return new ModelAndView("authorization", "error", Message.MAIL_NOT_UNIQUE);
            if(!password.equals(repeat_password))
                return new ModelAndView("authorization", "error", Message.PASSWORD_ERROR);

            User user = new User(login, mail, password);
            userService.addUser(user);
            return new ModelAndView("index");
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }
}
