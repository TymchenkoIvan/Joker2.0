package com.company.populator.entity.impl;

import com.company.entity.User;
import com.company.entity.bean.formbean.impl.SignUpForm;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserPopulatorTest {

    private UserPopulator populator = new UserPopulator();
    private SignUpForm signUpForm;

    @Before
    public void initTestData(){
        signUpForm = new SignUpForm();
        signUpForm.setLogin("user_login");
        signUpForm.setMail("user_mail");
        signUpForm.setPassword("user_password");
    }

    @Test
    public void givenSignUpFormThenPopulatesAllFieldsCorrectlyToJoke() {
        assertThat(populator.populate(signUpForm))
                .isNotNull()
                .isInstanceOf(User.class)
                .hasFieldOrPropertyWithValue("login", signUpForm.getLogin())
                .hasFieldOrPropertyWithValue("password", signUpForm.getPassword())
                .hasFieldOrPropertyWithValue("mail", signUpForm.getMail());
    }

}