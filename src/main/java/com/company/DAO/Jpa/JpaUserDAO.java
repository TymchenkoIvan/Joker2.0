package com.company.DAO.Jpa;

import com.company.DAO.UserDAO;
import com.company.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class JpaUserDAO implements UserDAO {

    @PersistenceContext
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
    public boolean isUserAdmin(String login) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);
        User user = (User)query.getSingleResult();

        return user.getRole().getRole().equals("admin");
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserByLogin(String login) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);

        return (User)query.getSingleResult();
    }

    @Override
    public User getUserById(int id) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", id);

        return (User)query.getSingleResult();
    }
}