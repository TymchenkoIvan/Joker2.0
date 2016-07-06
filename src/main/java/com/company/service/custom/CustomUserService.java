package com.company.service.custom;

import com.company.DAO.UserDAO;
import com.company.entity.User;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomUserService implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean isCorrectAction(int jokeId, String login) {
        return userDAO.isCorrectAction(jokeId, login);
    }

    @Override
    public boolean isUserAdmin(String login) {
        return userDAO.isUserAdmin(login);
    }

    @Override
    public boolean isSignInOk(String login, String password) {
        return userDAO.isSignInOk(login, password);
    }

    @Override
    public boolean isLoginUnique(String login) {
        return userDAO.isLoginUnique(login);
    }

    @Override
    public boolean isMailUnique(String mail) {
        return userDAO.isMailUnique(mail);
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }
}
