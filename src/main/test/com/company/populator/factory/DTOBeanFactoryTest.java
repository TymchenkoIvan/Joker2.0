package com.company.populator.factory;

import com.company.config.PopulatorConfig;
import com.company.entity.Joke;
import com.company.entity.Role;
import com.company.entity.Status;
import com.company.entity.User;
import com.company.entity.bean.dtobean.DTOBeans;
import com.company.entity.bean.dtobean.impl.JokeDTO;
import com.company.entity.bean.dtobean.impl.UserDTO;
import com.company.enums.Roles;
import com.company.enums.Statuses;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PopulatorConfig.class})
public class DTOBeanFactoryTest {

    private static final int MAX_NUM = 50;
    private static final int TEST_STRING_SIZE = 10;
    private Date currentDate = new Date();

    @Autowired
    private DTOBeanFactory factory;

    @Test
    public void givenJokeThenFactoryReturnsCorrectJokeDTO(){
        Joke testJoke = initJoke();

        assertThat(factory.create(DTOBeans.JokeDTO, testJoke))
                .isNotNull()
                .isInstanceOf(JokeDTO.class)
                .hasFieldOrPropertyWithValue("id", testJoke.getId())
                .hasFieldOrPropertyWithValue("likes", testJoke.getLikes())
                .hasFieldOrPropertyWithValue("dislikes", testJoke.getDislikes())
                .hasFieldOrPropertyWithValue("date", currentDate)
                .hasFieldOrPropertyWithValue("text", testJoke.getText())
                .hasFieldOrPropertyWithValue("status", testJoke.getStatus().getStatus())
                .hasFieldOrPropertyWithValue("userLogin", testJoke.getUser().getLogin());
    }

    @Test
    public void givenUserThenFactoryReturnsCorrectUserDTO(){
        User testUser = initUser();

        assertThat(factory.create(DTOBeans.UserDTO, testUser))
                .isNotNull()
                .isInstanceOf(UserDTO.class)
                .hasFieldOrPropertyWithValue("login", testUser.getLogin())
                .hasFieldOrPropertyWithValue("mail", testUser.getMail())
                .hasFieldOrPropertyWithValue("role", testUser.getRole().getRole())
                .hasFieldOrPropertyWithValue("id", testUser.getId());
    }

    private Joke initJoke() {
        Joke testJoke = new Joke();

        testJoke.setText(randomString());
        testJoke.setId(randomInt());
        testJoke.setDate(currentDate);
        testJoke.setDislikes(randomInt());
        testJoke.setLikes(randomInt());
        testJoke.setUser(initUser());
        testJoke.setStatus(initJokeStatus());

        return testJoke;
    }

    private Status initJokeStatus() {
        Status jokeStatus = new Status();
        jokeStatus.setStatus(Statuses.NEW.getStatus());

        return jokeStatus;
    }

    private User initUser() {
        User testUser = new User(randomString(), randomString(), randomString());
        testUser.setId(randomInt());
        testUser.setRole(initUserRole());

        return testUser;
    }

    private Role initUserRole() {
        Role userRole = new Role();
        userRole.setRole(Roles.USER.getRole());

        return userRole;
    }

    private String randomString(){
        return RandomStringUtils.randomAlphabetic(TEST_STRING_SIZE);
    }

    private int randomInt(){
        return (int)(Math.random() * MAX_NUM + 1);
    }
}