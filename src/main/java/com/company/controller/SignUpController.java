package com.company.controller;

import com.company.entity.User;
import com.company.exception.JokerValidationException;
import com.company.service.UserService;
import com.company.util.View;
import com.company.util.bean.SignUpForm;
import com.company.util.validator.SignUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private SignUpFormValidator signUpFormValidator;

    @RequestMapping("")
    public ModelAndView signUpPage() {
        return new ModelAndView(View.SIGN_UP_PAGE);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView createUser(@RequestParam(value="login") String login,
                                @RequestParam(value="mail") String mail,
                                @RequestParam(value="telephone") String telephone,
                                @RequestParam(value="password") String password,
                                @RequestParam(value="confirm") String confirm,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        SignUpForm formBean = createFormBean(login, mail, telephone, password, confirm);
        try {
            signUpFormValidator.validate(formBean);
        } catch (JokerValidationException ex) {
            return new ModelAndView(View.SIGN_UP_PAGE, "error", ex.getMessage());
        }
        User user = new User(login, mail, password);
        userService.addUser(user);
        return new ModelAndView("index");
    }

    private SignUpForm createFormBean(String login, String mail, String telephone, String password, String confirm) {
        SignUpForm dto = new SignUpForm();
        dto.setLogin(login);
        dto.setMail(mail);
        dto.setTelephone(telephone);
        dto.setPassword(password);
        dto.setConfirmPassword(confirm);
        return dto;
    }
}
