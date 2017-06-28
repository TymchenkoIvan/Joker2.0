package com.company.populator.factory;

import com.company.config.PopulatorConfig;
import com.company.entity.Joke;
import com.company.entity.User;
import com.company.entity.bean.formbean.impl.JokeForm;
import com.company.entity.bean.formbean.impl.SignUpForm;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PopulatorConfig.class})
public class EntityFactoryTest {

    private static final int TEST_STRING_SIZE = 10;
    private SignUpForm signUpForm;
    private JokeForm jokeForm;

    @Autowired
    private EntityFactory entityFactory;

    @Test
    public void givenSignUpFormThenFactoryReturnsUser(){
        initSignUpForm();

        assertThat(entityFactory.create(User.class, signUpForm))
                .isNotNull()
                .isInstanceOf(User.class)
                .hasFieldOrPropertyWithValue("login", signUpForm.getLogin())
                .hasFieldOrPropertyWithValue("password", signUpForm.getPassword())
                .hasFieldOrPropertyWithValue("mail", signUpForm.getMail());
    }

    @Test
    public void givenJokeFormThenFactoryReturnsJoke(){
        initJokeForm();

        assertThat(entityFactory.create(Joke.class, jokeForm))
                .isNotNull()
                .isInstanceOf(Joke.class)
                .hasFieldOrPropertyWithValue("text", jokeForm.getText())
                .hasFieldOrProperty("date");
    }

    private String randomString(){
        return RandomStringUtils.randomAlphabetic(TEST_STRING_SIZE);
    }

    private void initJokeForm() {
        jokeForm = new JokeForm();
        jokeForm.setText(randomString());
    }

    private void initSignUpForm() {
        signUpForm = new SignUpForm();
        signUpForm.setLogin(randomString());
        signUpForm.setMail(randomString());
        signUpForm.setPassword(randomString());
    }
}