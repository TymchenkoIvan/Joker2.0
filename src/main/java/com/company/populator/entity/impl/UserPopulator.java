package com.company.populator.entity.impl;

import com.company.entity.Entity;
import com.company.entity.User;
import com.company.entity.bean.formbean.FormBean;
import com.company.entity.bean.formbean.impl.SignUpForm;
import com.company.populator.entity.EntityPopulator;

public class UserPopulator implements EntityPopulator {

    @Override
    public Entity populate(FormBean formBean) {
        SignUpForm form = (SignUpForm)formBean;
        User user = new User();

        user.setLogin(form.getLogin());
        user.setPassword(form.getPassword());
        user.setMail(form.getMail());

        return user;
    }
}