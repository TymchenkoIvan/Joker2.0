package com.company.service.custom;

import com.company.DAO.RoleDAO;
import com.company.DAO.UserDAO;
import com.company.entity.User;
import com.company.entity.bean.dtobean.DTOBeans;
import com.company.entity.bean.dtobean.impl.UserDTO;
import com.company.entity.bean.formbean.impl.SignUpForm;
import com.company.populator.factory.DTOBeanFactory;
import com.company.populator.factory.EntityFactory;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomUserService implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private EntityFactory entityFactory;

    @Autowired
    private DTOBeanFactory dtoBeanFactory;

    @Override
    public boolean isUserAdmin(String login) {
        return userDAO.isUserAdmin(login);
    }

    @Override
    public boolean isLoginPairCorrect(String login, String password) {
        return userDAO.isLoginPairCorrect(login, password);
    }

    @Override
    public void createUser(SignUpForm formBean) {
        User user = (User) entityFactory.create(User.class, formBean);
        user.setRole(roleDAO.getRole("user"));
        userDAO.addUser(user);
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        User user = userDAO.getUserByLogin(login);
        return (UserDTO) dtoBeanFactory.create(DTOBeans.UserDTO, user);
    }

    @Override
    public boolean isSignUpInfoCorrect(SignUpForm form) {
        return form.getPassword().equals(form.getConfirm()) && (isLoginUnique(form.getLogin()) && isMailUnique(form.getMail()));
    }

    private boolean isLoginUnique(String login) {
        return userDAO.isLoginUnique(login);
    }

    private boolean isMailUnique(String mail) {
        return userDAO.isMailUnique(mail);
    }
}
