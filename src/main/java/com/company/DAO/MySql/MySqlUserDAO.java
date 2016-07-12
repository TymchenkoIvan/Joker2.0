package com.company.DAO.MySql;

import com.company.DAO.UserDAO;
import com.company.entity.Joke;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MySqlUserDAO implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean isLoginUnique(String login) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);

        return query.getResultList().size() == 0;
    }

    @Override
    public boolean isMailUnique(String mail) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.mail = :mail", User.class);
        query.setParameter("mail", mail);

        return query.getResultList().size() == 0;
    }

    @Override
    public boolean isLoginPairCorrect(String login, String password) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);

        return query.getResultList().size() == 1;
    }

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

    @Override
    public User getUser(String login) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);

        return (User)query.getSingleResult();
    }
}
