package com.company.DAO;

import com.company.Exceptions.JokerAppException;
import com.company.Exceptions.JokerDBException;
import com.company.config.Constants;
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


    private boolean isLoginOk(String login) {
        if(login.equals(null) || login.length() > Constants.SQL_LENGTH_LOGIN){
            return false;
        }
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);

        return query.getResultList().size() == 0;
    }

    private boolean isTelephoneOk(String telephone) {
        if(!telephone.equals(null) && telephone.length() > Constants.SQL_LENGTH_TELEPHONE){
            return false;
        }
        return true;
    }

    private boolean isPasswordOk(String password) {
        if(password.equals(null) || password.length() > Constants.SQL_LENGTH_PASSWORD){
            return false;
        }
        return true;
    }

    private boolean isMailOk(String mail) {
        if(mail.equals(null) || mail.length() > Constants.SQL_LENGTH_MAIL){
            return false;
        }

        Pattern pattern = Pattern.compile(Constants.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(mail);
        if(!matcher.matches()){
            return false;
        }

        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.mail = :mail", User.class);
        query.setParameter("mail", mail);
        return query.getResultList().size() == 0;
    }

    @Override
    public boolean isSignInOk(String login, String password) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        if(query.getResultList().size() != 1)
            return false;
        return true;
    }

    @Override
    public boolean isCorrectAction(int jokeId, String login) throws JokerDBException {
        boolean isCorrect = false;
        try {
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
            query.setParameter("login", login);
            User user = (User)query.getSingleResult();
            Joke joke = entityManager.find(Joke.class, jokeId);

            if(isCorrect = !user.getRatedJokes().contains(joke))
                user.addRatedJoke(joke);

        } catch (Exception ex) {
            throw new JokerDBException(ex.getMessage());
        }
        return isCorrect;
    }

    @Override
    public boolean isUserAdmin(String login) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);
        User user = (User)query.getSingleResult();

        return user.getRole()!=null && user.getRole().equals("admin");
    }

    @Override
    public User createUser(String login, String mail, String telephone, String password) throws JokerAppException {
        if(!isLoginOk(login)){
            throw new JokerAppException(Constants.ERROR_LOGIN);
        }
        if(!isMailOk(mail)){
            throw new JokerAppException(Constants.ERROR_MAIL);
        }
        if(!isTelephoneOk(telephone)){
            throw new JokerAppException(Constants.ERROR_TELEPHONE);
        }
        if(!isPasswordOk(password)){
            throw new JokerAppException(Constants.ERROR_PASSWORD);
        }
        return new User(login, mail, telephone, password);
    }

    @Override
    public User getUserByLogin(String login) throws JokerDBException {
        try {
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
            query.setParameter("login", login);
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception ex){
            throw new JokerDBException(ex.getMessage());
        }
    }

    @Override
    public void addUser(User user) throws JokerDBException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new JokerDBException(ex.getMessage());
        }
    }
}