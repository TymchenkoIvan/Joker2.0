package com.company.DAO;

import com.company.entity.User;

public interface UserDAO {
    boolean isLoginUnique(String login);
    boolean isMailUnique(String mail);
    boolean isSignInOk(String login, String password);
    boolean isCorrectAction(int jokeId, String login);
    void addUser(User user);
}
