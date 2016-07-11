package com.company.populator.formbean;

import com.company.entity.bean.formbean.FormBean;
import com.company.entity.bean.formbean.impl.SignUpForm;

import javax.servlet.http.HttpServletRequest;

public class SignUpPopulator implements FormBeanPopulator {

    @Override
    public FormBean populate(HttpServletRequest req) {
        SignUpForm bean = new SignUpForm();

        bean.setLogin(req.getParameter("login"));
        bean.setMail(req.getParameter("mail"));
        bean.setTelephone(req.getParameter("telephone"));
        bean.setPassword(req.getParameter("password"));
        bean.setConfirmPassword(req.getParameter("confirm"));

        return bean;
    }
}
