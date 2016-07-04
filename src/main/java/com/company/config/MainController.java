package com.company.config;

import com.company.DAO.JokeDAO;
import com.company.DAO.UserDAO;
import com.company.entity.Joke;
import com.company.entity.User;
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

    private final String SIGN_IN_ERROR = "ERROR: Wrong pair Login/Password, try again";
    private final String LOGIN_ERROR = "ERROR: Login is not unique, try again";
    private final String MAIL_NOT_UNIQUE = "ERROR: Mail is not unique, try again";
    private final String MAIL_NOT_REAL = "ERROR: Mail is not real, try again";
    private final String PASSWORD_ERROR = "ERROR: Password repeat is wrong, try again";
    private final String IS_ADMIN_ERROR = "ERROR: Only admin can delete/recover jokes";
    private final String VOTE_ERROR = "ERROR: You can't rate one joke twice";

	@Autowired
	private JokeDAO jokeDAO;

    @Autowired
    private UserDAO userDAO;

	@RequestMapping("/index")
	public ModelAndView listJokes() {
		return new ModelAndView("index", "jokes", jokeDAO.list());
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
        return new ModelAndView("archive", "jokes", jokeDAO.listArchive());
    }

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addAdv(@RequestParam(value="text") String text,
						 HttpServletRequest request,
						 HttpServletResponse response)
	{
		try {
			Joke joke = new Joke(text);
            jokeDAO.add(joke);
			return new ModelAndView("index", "jokes", jokeDAO.list());
		} catch (Exception ex) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}

    @RequestMapping("/like")
    public ModelAndView like(@RequestParam(value="jokeId") int jokeId,
                             @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<>();
        if(userDAO.isCorrectAction(jokeId, login)){
            jokeDAO.like(jokeId);
            model.put("jokes", jokeDAO.list());
            return new ModelAndView("index", model);
        }
        model.put("jokes", jokeDAO.list());
        model.put("error", VOTE_ERROR);
        return new ModelAndView("index", model);
    }

    @RequestMapping("/dislike")
     public ModelAndView dislike(@RequestParam(value="jokeId") int jokeId,
                                 @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<>();
        if(userDAO.isCorrectAction(jokeId, login)) {
            jokeDAO.dislike(jokeId);
            model.put("jokes", jokeDAO.list());
            return new ModelAndView("index", model);
        }
        model.put("jokes", jokeDAO.list());
        model.put("error", VOTE_ERROR);
        return new ModelAndView("index", model);
    }

    @RequestMapping("/archive/recover")
    public ModelAndView recover(@RequestParam(value="jokeId") int jokeId,
                                @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<>();
        if(userDAO.isUserAdmin(login)) {
            jokeDAO.recover(jokeId);
            model.put("jokes", jokeDAO.listArchive());
            return new ModelAndView("archive", model);
        }
        model.put("jokes", jokeDAO.listArchive());
        model.put("error", IS_ADMIN_ERROR);
        return new ModelAndView("archive", model);
    }

    @RequestMapping("/archive/delete")
    public ModelAndView delete(@RequestParam(value="jokeId") int jokeId,
                               @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<>();
        if(userDAO.isUserAdmin(login)) {
            jokeDAO.delete(jokeId);
            model.put("jokes", jokeDAO.listArchive());
            return new ModelAndView("archive", model);
        }
        model.put("jokes", jokeDAO.listArchive());
        model.put("error", IS_ADMIN_ERROR);
        return new ModelAndView("archive", model);
    }

    @RequestMapping("/sign_in/sign_in")
    public ModelAndView signIn(@RequestParam(value="login") String login,
                               @RequestParam(value="password") String password,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        if(userDAO.isSignInOk(login, password)){
            Cookie cookie = new Cookie("jokerUser", login);
            cookie.setMaxAge(365*24*60*60);
            cookie.setPath("/");
            response.addCookie(cookie);

            Map<String, Object> model = new HashMap<>();
            model.put("jokes", jokeDAO.list());
            return new ModelAndView("index", model);
        }
        else
            return new ModelAndView("sign_in", "error", SIGN_IN_ERROR);
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
            if(!userDAO.isLoginUnique(login))
                return new ModelAndView("authorization", "error", LOGIN_ERROR);
            if(!userDAO.isMailReal(mail))
                return new ModelAndView("authorization", "error", MAIL_NOT_REAL);
            if(!userDAO.isMailUnique(mail))
                return new ModelAndView("authorization", "error", MAIL_NOT_UNIQUE);
            if(!password.equals(repeat_password))
                return new ModelAndView("authorization", "error", PASSWORD_ERROR);

            User user = new User(login, mail, password);
            userDAO.addUser(user);
            return new ModelAndView("index");
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }
}