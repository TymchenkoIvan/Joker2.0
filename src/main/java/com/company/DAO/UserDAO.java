package com.company.DAO;

import com.company.Exceptions.JokerAppException;
import com.company.Exceptions.JokerDBException;
import com.company.entity.User;

public interface UserDAO {

    boolean isSignInOk(String login, String password);
    boolean isCorrectAction(int jokeId, String login);
    boolean isUserAdmin(String login);
    User createUser(String login, String mail, String telephone, String password) throws JokerAppException;
    User getUserByLogin(String login) throws JokerDBException;
    void addUser(User user);
}
