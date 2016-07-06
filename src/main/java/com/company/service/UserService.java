package com.company.service;

import com.company.entity.User;

public interface UserService {

    boolean isCorrectAction(int jokeId, String login);

    boolean isUserAdmin(String login);

    boolean isSignInOk(String login, String password);

    boolean isLoginUnique(String login);

    boolean isMailUnique(String mail);

    void addUser(User user);
}
