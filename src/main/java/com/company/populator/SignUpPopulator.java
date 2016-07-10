package com.company.populator;

import com.company.entity.bean.Bean;
import com.company.entity.bean.formbean.SignUpForm;

import javax.servlet.http.HttpServletRequest;

public class SignUpPopulator implements BeanPopulator {

    @Override
    public Bean populate(HttpServletRequest req) {
        SignUpForm bean = new SignUpForm();

        bean.setLogin(req.getParameter("login"));
        bean.setMail(req.getParameter("mail"));
        bean.setTelephone(req.getParameter("telephone"));
        bean.setPassword(req.getParameter("password"));
        bean.setConfirmPassword(req.getParameter("confirm"));

        return bean;
    }
}
