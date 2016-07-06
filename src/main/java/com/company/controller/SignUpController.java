package com.company.controller;

import com.company.entity.User;
import com.company.exception.JokerValidationException;
import com.company.service.UserService;
import com.company.util.View;
import com.company.util.ModelName;
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
    private SignUpFormValidator formValidator;

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
        try {
            SignUpForm formBean = createFormBean(login, mail, telephone, password, confirm);
            formValidator.validate(formBean);

            User user = new User(login, mail, password);
            userService.addUser(user);
        } catch (JokerValidationException e) {
            e.printStackTrace();
            return new ModelAndView(View.SIGN_UP_PAGE, ModelName.ALL_PAGES_ERROR_MESSAGE, e.getMessage());
        }
        return new ModelAndView(View.INDEX_PAGE);
    }

    private SignUpForm createFormBean(String login, String mail, String telephone, String password, String confirm) {
        SignUpForm bean = new SignUpForm();

        bean.setLogin(login);
        bean.setMail(mail);
        bean.setTelephone(telephone);
        bean.setPassword(password);
        bean.setConfirmPassword(confirm);

        return bean;
    }
}
