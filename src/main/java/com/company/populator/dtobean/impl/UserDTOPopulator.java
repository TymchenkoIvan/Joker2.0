package com.company.populator.dtobean.impl;

import com.company.entity.Entity;
import com.company.entity.User;
import com.company.entity.bean.dtobean.DTOBean;
import com.company.entity.bean.dtobean.impl.UserDTO;
import com.company.populator.dtobean.DTOPopulator;

public class UserDTOPopulator implements DTOPopulator{

    @Override
    public DTOBean populate(Entity entity) {
        User user = (User) entity;
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setMark(user.getMark());
        userDTO.setMail(user.getMail());

        return userDTO;
    }
}
