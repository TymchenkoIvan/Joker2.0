package com.company.service;

import com.company.entity.bean.dtobean.DTOBean;
import com.company.entity.bean.formbean.impl.SignUpForm;

public interface UserService {

    boolean isUserAdmin(String login);

    boolean isLoginPairCorrect(String login, String password);

    void createUser(SignUpForm formBean);

    DTOBean getUserByLogin(String login);

    boolean isSignUpInfoCorrect(SignUpForm form);
}
