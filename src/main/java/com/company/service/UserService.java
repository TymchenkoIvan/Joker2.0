package com.company.service;

import com.company.entity.bean.formbean.impl.SignUpForm;

public interface UserService {

    boolean isCorrectAction(int jokeId, String login);

    boolean isUserAdmin(String login);

    boolean isLoginPairCorrect(String login, String password);

    boolean isLoginUnique(String login);

    boolean isMailUnique(String mail);

    void createUser(SignUpForm formBean);
}
