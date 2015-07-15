package com.company.config;

import com.company.DAO.JokeDAO;
import com.company.DAO.UserDAO;
import com.company.Exceptions.JokerAppException;
import com.company.entity.Joke;
import com.company.entity.User;
import com.company.enums.Statuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private JokeDAO jokeDAO;

    @Autowired
    private UserDAO userDAO;

    private int getPagesCount(int count){
        return count % Constants.MAX_JOKES_ON_PAGE == 0
                ? count/Constants.MAX_JOKES_ON_PAGE
                : count/Constants.MAX_JOKES_ON_PAGE +1;
    }

    private ModelAndView getModelIndex(int page){
        Map<String, Object> model = new HashMap<>();
        int pages = getPagesCount(jokeDAO.jokesCountByStatus(Statuses.NEW));
        model.put(Constants.VAR_PAGER_COUNT, pages);
        model.put(Constants.VAR_PAGE_COUNT, page);
        model.put(Constants.VAR_JOKES_LIST, jokeDAO.list(Statuses.NEW, page));

        return new ModelAndView(Constants.PAGE_INDEX, model);
    }

    private ModelAndView getModelIndex(int page, String error){
        Map<String, Object> model = new HashMap<>();
        int pages = getPagesCount(jokeDAO.jokesCountByStatus(Statuses.NEW));
        model.put(Constants.VAR_PAGER_COUNT, pages);
        model.put(Constants.VAR_PAGE_COUNT, page);
        model.put(Constants.VAR_ERROR, error);
        model.put(Constants.VAR_JOKES_LIST, jokeDAO.list(Statuses.NEW, page));

        return new ModelAndView(Constants.PAGE_INDEX, model);
    }

    @RequestMapping("/index")
    public ModelAndView listJokes(@RequestParam(value="page") int page) {
        return getModelIndex(page);
    }

    @RequestMapping("/sign_in")
    public ModelAndView signIn() {
        return new ModelAndView(Constants.PAGE_SIGN);
    }

    @RequestMapping("/")
    public ModelAndView signInPage(){
        return getModelIndex(1);
    }

	@RequestMapping(value = "/add_page")
	public String addPage() {
		return Constants.PAGE_ADD;
	}

    @RequestMapping("/archive")
    public ModelAndView listJokesArchive() {
        return new ModelAndView(Constants.PAGE_ARCHIVE, Constants.VAR_JOKES_LIST, jokeDAO.listArchive());
    }

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addAdv(@RequestParam(value="text") String text,
                               @RequestParam(value=Constants.VAR_LOGIN) String login,
						        HttpServletResponse response){
		try {
            User user = userDAO.getUserByLogin(login);
			Joke joke = new Joke(user, text);
            jokeDAO.add(joke);
            return getModelIndex(1);
        }catch (JokerAppException ex){
            return new ModelAndView(Constants.PAGE_ADD, Constants.VAR_ERROR, ex.getMessage());
        } catch (Exception ex) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}

    @RequestMapping("/like")
    public ModelAndView like(@RequestParam(value=Constants.VAR_JOKE_ID) int jokeId,
                             @RequestParam(value=Constants.VAR_LOGIN) String login,
                             @RequestParam(value=Constants.VAR_PAGE_COUNT) int page,
                             HttpServletResponse response) {
        try {
            if (!userDAO.isCorrectAction(jokeId, login)) {
                throw new JokerAppException(Constants.ERROR_VOTE);
            }
            jokeDAO.like(jokeId);
            return getModelIndex(page);
        }catch (JokerAppException ex){
            return getModelIndex(page, ex.getMessage());
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    @RequestMapping("/dislike")
     public ModelAndView dislike(@RequestParam(value=Constants.VAR_JOKE_ID) int jokeId,
                                 @RequestParam(value=Constants.VAR_LOGIN) String login,
                                 @RequestParam(value=Constants.VAR_PAGE_COUNT) int page,
                                 HttpServletResponse response) {
        try {
            if (!userDAO.isCorrectAction(jokeId, login)) {
                throw new JokerAppException(Constants.ERROR_VOTE);
            }
            jokeDAO.dislike(jokeId);
            return getModelIndex(page);
        }catch (JokerAppException ex){
            return getModelIndex(page, ex.getMessage());
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    @RequestMapping("/sign_in/sign_in")
    public ModelAndView signIn(@RequestParam(value=Constants.VAR_LOGIN) String login,
                               @RequestParam(value=Constants.VAR_PASSWORD) String password,
                               HttpServletResponse response) {
        try{
            if(userDAO.isSignInOk(login, password)){
                throw new JokerAppException(Constants.ERROR_SIGN_IN);
            }

            Cookie cookie = new Cookie(Constants.VAR_COOKIE, login);
            cookie.setMaxAge(365*24*60*60);
            cookie.setPath("/");
            response.addCookie(cookie);

            return getModelIndex(1);
        }catch (JokerAppException ex){
            return new ModelAndView(Constants.PAGE_SIGN, Constants.VAR_ERROR, ex.getMessage());
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    @RequestMapping("/sign_out")
    public ModelAndView signOut(HttpServletResponse response) {
        response.setContentType("text/html");

        Cookie cookie = new Cookie(Constants.VAR_COOKIE, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new ModelAndView(Constants.PAGE_SIGN);
    }

    @RequestMapping("/faq")
    public ModelAndView faq() {
        return new ModelAndView(Constants.PAGE_FAQ);
    }


    @RequestMapping("/authorization")
    public ModelAndView authorization() {
        return new ModelAndView(Constants.PAGE_AUTO);
    }

    @RequestMapping("/adduser")
    public ModelAndView addUser(@RequestParam(value=Constants.VAR_LOGIN) String login,
                                @RequestParam(value=Constants.VAR_MAIL) String mail,
                                @RequestParam(value=Constants.VAR_TELEPHONE) String telephone,
                                @RequestParam(value=Constants.VAR_PASSWORD) String password,
                                @RequestParam(value=Constants.VAR_PASSWORD_REPEAT) String repeat_password,
                                HttpServletResponse response) {
        try {
            if(!password.equals(repeat_password)){
                throw new JokerAppException(Constants.ERROR_PASSWORD);
            }
            User user = userDAO.createUser(login, mail, telephone, password);
            userDAO.addUser(user);
            return new ModelAndView(Constants.PAGE_AUTO);
        } catch (JokerAppException ex) {
            return new ModelAndView(Constants.PAGE_AUTO, Constants.VAR_ERROR, ex.getMessage());
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }
}