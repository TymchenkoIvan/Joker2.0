package com.company.DAO;

import com.company.entity.User;

public interface UserDAO {
    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    boolean isLoginUnique(String login);
    boolean isMailUnique(String mail);
    boolean isMailReal(String mail);
    boolean isSignInOk(String login, String password);
    boolean isCorrectAction(int jokeId, String login);
    boolean isUserAdmin(String login);
    User getUserByLogin(String login);
    void addUser(User user);
}
