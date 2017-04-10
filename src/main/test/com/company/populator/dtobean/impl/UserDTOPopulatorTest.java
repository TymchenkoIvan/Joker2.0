package com.company.populator.dtobean.impl;

import com.company.entity.Role;
import com.company.entity.User;
import com.company.entity.bean.dtobean.impl.UserDTO;
import com.company.enums.Roles;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserDTOPopulatorTest {

    public static final String USER_LOGIN = "userLogin";
    public static final String USER_MAIL = "userMail";
    private User testUser;
    private Role userRole;

    public static final int ID = 1;

    {
        userRole = new Role();
        userRole.setRole(Roles.USER.getRole());

        testUser = new User(USER_LOGIN, USER_MAIL, "userPassword");
        testUser.setId(ID);
        testUser.setRole(userRole);
    }

    private UserDTOPopulator populator = new UserDTOPopulator();

    @Test
    public void givenUserEntityThenPopulatesAllFieldsCorrectly(){
        assertThat(populator.populate(testUser))
                .isNotNull()
                .isInstanceOf(UserDTO.class)
                .hasFieldOrPropertyWithValue("login", USER_LOGIN)
                .hasFieldOrPropertyWithValue("mail", USER_MAIL)
                .hasFieldOrPropertyWithValue("role", Roles.USER.getRole())
                .hasFieldOrPropertyWithValue("id", ID);
    }
}