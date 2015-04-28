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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/com_company")
public class MainController {

    private final String SIGN_IN_ERROR = "ERROR: Wrong pair Login/Password, try again";
    private final String LOGIN_ERROR = "ERROR: Login is not unique, try again";
    private final String MAIL_NOT_UNIQUE = "ERROR: Mail is not unique, try again";
    private final String MAIL_NOT_REAL = "ERROR: Mail is not real, try again";
    private final String PASSWORD_ERROR = "ERROR: Password repeat is wrong, try again";
    private final String VOTE_ERROR = "ERROR: You can't rate one joke twice";

	@Autowired
	private JokeDAO jokeDAO;

    @Autowired
    private UserDAO userDAO;


    /**
     * Это вывод на главной странице по умолчанию.
     */
	@RequestMapping("/index")
	public ModelAndView listJokes() {
		return new ModelAndView("index", "jokes", jokeDAO.list());
	}


    /**
     * Это первая(корневая) страница приложения, на которой пользователь может ввести свой логин/пароль.
     */
    @RequestMapping("/")
    public ModelAndView signInPage() {
        return new ModelAndView("sign_in");
    }


    /**
     * Переход на страницу добавления новой шутки.
     */
	@RequestMapping(value = "/add_page")
	public String addPage(Model model) {
		return "add_page";
	}


    /**
     * Переход на страницу архива. Тут будут выведены только шутки, которые не понравились пользователям или отравленны админами в архив.
     * Такие шутки помечены joke.mark = "archiv".
     */
    @RequestMapping("/archive")
    public ModelAndView listJokesArchive() {
        return new ModelAndView("archive", "jokes", jokeDAO.listArchive());
    }


    /**
     * Тут мы принимаем текст новой шутки, создаем ее, добавляем, и возвращаяем обновленную главную страницу.
     */
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


    /**
     * Принимается id шутки и логин пользователя.
     * Проверяется, может ли пользователь голосовать, и если да, то голосование проходит и возвращается главная страница.
     * В противном случае голосование глушится и пользователю выводится главная страница с
     * соответствущим объяснением недоразумения.
     */
    @RequestMapping("/like")
    public ModelAndView like(@RequestParam(value="jokeId") int jokeId,
                             @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<String, Object>();
        if(userDAO.isCorrectAction(jokeId, login)){
            jokeDAO.like(jokeId);
            model.put("jokes", jokeDAO.list());
            return new ModelAndView("index", model);
        }
        model.put("jokes", jokeDAO.list());
        model.put("error", VOTE_ERROR);
        return new ModelAndView("index", model);
    }


    /**
     * Принимается id шутки и логин пользователя.
     * Проверятся может ли пользователь голосовать и если да, то голосование проходит, возвращается главная страница.
     * В противном случае голосование глушится и пользователю выводится главная страница с
     * соответствущим объяснением недоразумения.
     */
    @RequestMapping("/dislike")
     public ModelAndView dislike(@RequestParam(value="jokeId") int jokeId,
                                 @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<String, Object>();
        if(userDAO.isCorrectAction(jokeId, login)) {
            jokeDAO.dislike(jokeId);
            model.put("jokes", jokeDAO.list());
            return new ModelAndView("index", model);
        }
        model.put("jokes", jokeDAO.list());
        model.put("error", VOTE_ERROR);
        return new ModelAndView("index", model);
    }


    /**
     * Тут мы получаем пару логин/пароль.
     * Проверяем введенную инцу на адекватность, и если нет, то возвращаем на прежнюю страницу и выводим
     * сообщение о неправильности ввода данных.
     * Если все ок, то добавляем cookie и возвращаем главную страницу.
     */
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

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("jokes", jokeDAO.list());
            return new ModelAndView("index", model);
        }
        else
            return new ModelAndView("sign_in", "error", SIGN_IN_ERROR);
    }


    /**
     * Этим вызовом мы удаляем cookie(созданием нового с нулевой продолжительностью) и возвращаем страницу входа.
     */
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


    /**
     * Переход на страницу FAQ, где описано как пользоваться приложением и прочая полезная/интересная инфа:).
     */
    @RequestMapping("/faq")
    public ModelAndView faq() {
        return new ModelAndView("faq");
    }


    /**
     * Тут мы переносим юзера к странице, где он может создать нового пользователя.
     */
    @RequestMapping("/authorization")
    public ModelAndView authorization() {
        return new ModelAndView("authorization");
    }


    /**
     * Создаем нового пользователя.
     * Мы получаем введенные новые данные.
     * все поочередно проверяется на адекватность и если что-то идет не так, то возвращается текст с соответствующей подсказкой,
     * так что бы было понятно что введено не верно.
     * Если все ок, то создается новый пользователь и передается страница входа через которую можно будет покасть на ресурс.
     */
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
            return new ModelAndView("sign_in");
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    // ----------Ниже описана работа с административной частью ресурса-----------

    /**
     * Тут мы получаем пару логин/пароль админа.
     * Проверяем введенную инфу на адекватность, и если нет, то возвращаем на прежнюю страницу и выводим
     * сообщение о неправильности ввода данных.
     * Если все ок, то добавляем cookie и возвращаем главную страницу.
     * Продолжительность жизни кукисов - одни сутки.
     */
    @RequestMapping("admin/admin/sign_in")
    public ModelAndView adminSignIn(@RequestParam(value="login") String login,
                               @RequestParam(value="password") String password,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        if(userDAO.isSignInOk(login, password) && userDAO.isUserAdmin(login)){
            Cookie cookie = new Cookie("jokerAdmin", login);
            cookie.setMaxAge(24*60*60);
            cookie.setPath("/");
            response.addCookie(cookie);

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("jokes", jokeDAO.list());
            model.put("jokesArchive", jokeDAO.listArchive());
            model.put("deleted", jokeDAO.listDeleted());
            return new ModelAndView("admin/jokes", model);
        }
        else
            return new ModelAndView("admin/admin", "error", SIGN_IN_ERROR);
    }


    /**
     * Тут мы переносим юзера к странице, где он может создать войти в панель управления.
     */
    @RequestMapping("/admin/admin")
    public ModelAndView adminAuthorization() {
        return new ModelAndView("admin/admin");
    }


    /**
     * Это вывод всех шуток для админа. Здесь он сможет удалять шутки, воставливать и отправлять в архив.
     */
    @RequestMapping("/admin/jokes")
    public ModelAndView allJokes() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("jokes", jokeDAO.list());
        model.put("jokesArchive", jokeDAO.listArchive());
        model.put("deleted", jokeDAO.listDeleted());
        return new ModelAndView("admin/jokes", model);
    }


    /**
     * Это первая(корневая) страница приложения, на которой пользователь может ввести свой логин/пароль.
     */
    @RequestMapping("/admin")
    public ModelAndView adminSignInPage() {
        return new ModelAndView("admin/admin");
    }


    /**
     * Этим вызовом мы удаляем cookie(созданием нового с нулевой продолжительностью) и возвращаем страницу входа.
     */
    @RequestMapping("/admin/sign_out")
    public ModelAndView adminSignOut(HttpServletRequest request,
                                HttpServletResponse response) {
        response.setContentType("text/html");
        Cookie cookie = new Cookie("jokerAdmin", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ModelAndView("admin/admin");
    }


    /**
     * Принимается id шутки и логин пользователя.
     * Проверятся админ это или нет.
     * Если да, то шутка восстанавливается. На самом деле создается новая шутка со старым текстом, а старая
     * помечается как "deleted".
     * В противном случае восстановление глушится и пользователю выводится страница архива с
     * соответствущим объяснением недоразумения.
     */
    @RequestMapping("/admin/jokes/recover")
    public ModelAndView recover(@RequestParam(value="jokeId") int jokeId,
                                @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<String, Object>();
        if(userDAO.isUserAdmin(login))
            jokeDAO.recover(jokeId);

        model.put("jokes", jokeDAO.list());
        model.put("jokesArchive", jokeDAO.listArchive());
        model.put("deleted", jokeDAO.listDeleted());
        return new ModelAndView("admin/jokes", model);
    }


    /**
     * Принимается id шутки и логин администратора.
     * Проверятся админ это или нет(вероятно лишняя проверка, но пусть будет).
     * Если да, то шутке ставится метка "deleted" и она теперь нигде не отображается.
     * В противном случае изменения глушатся и пользователю выводится страница шуток.
     */
    @RequestMapping("/admin/jokes/delete")
    public ModelAndView delete(@RequestParam(value="jokeId") int jokeId,
                               @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<String, Object>();
        if(userDAO.isUserAdmin(login))
            jokeDAO.delete(jokeId);

        model.put("jokes", jokeDAO.list());
        model.put("jokesArchive", jokeDAO.listArchive());
        model.put("deleted", jokeDAO.listDeleted());
        return new ModelAndView("admin/jokes", model);
    }


    /**
     * Принимается id шутки и логин пользователя.
     * Проверятся админ это или нет(вероятно лишняя проверка, но пусть будет).
     * Если да, то шутке ставится метка "archive" и теперь она отображается в архиве.
     * В противном случае изменения глушатся и пользователю выводится страница шуток.
     */
    @RequestMapping("/admin/jokes/toArchive")
    public ModelAndView toArchive(@RequestParam(value="jokeId") int jokeId,
                               @RequestParam(value="login") String login) {
        Map<String, Object> model = new HashMap<String, Object>();
        if(userDAO.isUserAdmin(login))
            jokeDAO.toArchive(jokeId);

        model.put("jokes", jokeDAO.list());
        model.put("jokesArchive", jokeDAO.listArchive());
        model.put("deleted", jokeDAO.listDeleted());
        return new ModelAndView("admin/jokes", model);
    }


    /**
     * Выводится страница с информацией о всех зарегистрированных пользователях ресурса.
     */
    @RequestMapping("/admin/users")
    public ModelAndView users() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("users", userDAO.list());
        return new ModelAndView("admin/users", model);
    }
}