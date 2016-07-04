package com.company.service.custom;

import com.company.DAO.UserDAO;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomUserService implements UserService{

    @Autowired
    private UserDAO userDAO;
}
