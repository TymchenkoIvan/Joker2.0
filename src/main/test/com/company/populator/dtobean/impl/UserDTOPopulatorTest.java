package com.company.populator.dtobean.impl;

import com.company.entity.Role;
import com.company.entity.User;
import com.company.entity.bean.dtobean.impl.UserDTO;
import com.company.enums.Roles;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserDTOPopulatorTest {

    private User testUser;

    {
        Role userRole = new Role();
        userRole.setRole(Roles.USER.getRole());

        testUser = new User("userLogin", "userMail", "userPassword");
        testUser.setId(1);
        testUser.setRole(userRole);
    }

    private UserDTOPopulator populator = new UserDTOPopulator();

    @Test
    public void givenUserEntityThenPopulatesAllFieldsCorrectly(){
        assertThat(populator.populate(testUser))
                .isNotNull()
                .isInstanceOf(UserDTO.class)
                .hasFieldOrPropertyWithValue("login", testUser.getLogin())
                .hasFieldOrPropertyWithValue("mail", testUser.getMail())
                .hasFieldOrPropertyWithValue("role", testUser.getRole().getRole())
                .hasFieldOrPropertyWithValue("id", testUser.getId());
    }
}