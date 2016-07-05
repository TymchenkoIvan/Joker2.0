package com.company.config;

import com.company.entity.User;
import com.company.util.Message;
import com.company.service.JokeService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/")
public class MainController {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private UserService userService;

	@RequestMapping("/index")
	public ModelAndView listJokes() {
		return new ModelAndView("index", "jokes", jokeService.getAllJokes());
	}

    @RequestMapping("/")
    public ModelAndView signInPage() {
        return new ModelAndView("sign_in");
    }

	@RequestMapping(value = "/add_page")
	public String addPage(Model model) {
		return "add_page";
	}

    @RequestMapping("/archive")
    public ModelAndView listJokesArchive() {
        return new ModelAndView("archive", "jokes", jokeService.getArchivedJokes());
    }

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addAdv(@RequestParam(value="text") String text,
						 HttpServletRequest request,
						 HttpServletResponse response)
	{
		try {
            jokeService.addJoke(text);
			return new ModelAndView("index", "jokes", jokeService.getAllJokes());
		} catch (Exception ex) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
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

    @RequestMapping("/archive/recover")
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

    @RequestMapping("/archive/delete")
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

    @RequestMapping("/sign_in/sign_in")
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
            return new ModelAndView("sign_in", "error", Message.SIGN_IN_ERROR);
    }

    @RequestMapping("/sign_out")
    public ModelAndView signOut(HttpServletRequest request,
                                HttpServletResponse response) {
        response.setContentType("text/html");
        Cookie cookie = new Cookie("jokerUser", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ModelAndView("sign_in");
    }

    @RequestMapping("/faq")
    public ModelAndView faq() {
        return new ModelAndView("faq");
    }

    @RequestMapping("/authorization")
    public ModelAndView authorization() {
        return new ModelAndView("authorization");
    }

    @RequestMapping("/adduser")
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