package com.company.DAO;

import com.company.entity.Joke;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDAOImpl implements UserDAO{

    @Autowired
    private EntityManager entityManager;

    /**
     * Метод проверяет уникален переданный login или нет.
     * @param login
     * @return
     */
    @Override
    public boolean isLoginUnique(String login) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);

        return query.getResultList().size() == 0;
    }

    /**
     * Метод проверяет реален переданный e-mail или нет.
     * @param mail
     * @return
     */
    @Override
    public boolean isMailReal(String mail) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(mail);

        return matcher.matches();
    }

    /**
     * Метод проверяет уникален переданный e-mail или нет.
     * @param mail
     * @return
     */
    @Override
    public boolean isMailUnique(String mail) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.mail = :mail", User.class);
        query.setParameter("mail", mail);

        return query.getResultList().size() == 0;
    }

    /**
     * Метод проверяет правильно ли введена пара login/password.
     * @param login
     * @param password
     * @return
     */
    @Override
    public boolean isSignInOk(String login, String password) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        if(query.getResultList().size() != 1)
            return false;

        return true;
    }

    /**
     * Метод проверяет может User(ищет по логину) голосовать за Joke(поиск по id).
     * Напоминаю, User может голосовать за каждую Joke один раз.
     * @param jokeId
     * @param login
     * @return
     */
    @Override
    public boolean isCorrectAction(int jokeId, String login) {
        boolean isCorrect = false;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
            query.setParameter("login", login);
            User user = (User)query.getSingleResult();
            Joke joke = entityManager.find(Joke.class, jokeId);

            if(isCorrect = !user.getJokes().contains(joke))
                user.addJoke(joke);

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
        return isCorrect;
    }

    @Override
    public boolean isUserAdmin(String login) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);
        User user = (User)query.getSingleResult();

        return user.getMark()!=null && user.getMark().equals("admin");
    }

    /**
     * Добавляется переданный User.
     * @param user
     */
    @Override
    public void addUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
