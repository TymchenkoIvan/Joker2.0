package com.company.populator.dtobean.impl;

import com.company.entity.Joke;
import com.company.entity.Role;
import com.company.entity.Status;
import com.company.entity.User;
import com.company.entity.bean.dtobean.impl.JokeDTO;
import com.company.enums.Roles;
import com.company.enums.Statuses;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class JokeDTOPopulatorTest {

    private JokeDTOPopulator populator = new JokeDTOPopulator();
    private Joke testJoke;
    private Date currentDate = new Date();

    @Before
    public void initTestData(){
        Role userRole = initUserRole();
        User testUser = initUser(userRole);
        Status jokeStatus = initJokeStatus();
        initJoke(testUser, jokeStatus);
    }

    private void initJoke(User testUser, Status jokeStatus) {
        testJoke = new Joke();
        testJoke.setText("Text example");
        testJoke.setId(1);
        testJoke.setDate(currentDate);
        testJoke.setDislikes(4);
        testJoke.setLikes(3);
        testJoke.setUser(testUser);
        testJoke.setStatus(jokeStatus);
    }

    private Status initJokeStatus() {
        Status jokeStatus = new Status();
        jokeStatus.setStatus(Statuses.NEW.getStatus());

        return jokeStatus;
    }

    private User initUser(Role userRole) {
        User testUser = new User("userLogin", "userMail", "userPassword");
        testUser.setId(1);
        testUser.setRole(userRole);

        return testUser;
    }

    private Role initUserRole() {
        Role userRole = new Role();
        userRole.setRole(Roles.USER.getRole());

        return userRole;
    }

    @Test
    public void givenJokeThenPopulatesAllFieldsCorrectlyToJokeDTO() throws Exception {
        assertThat(populator.populate(testJoke))
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
}