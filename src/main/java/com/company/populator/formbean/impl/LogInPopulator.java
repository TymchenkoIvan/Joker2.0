package com.company.populator.formbean.impl;

import com.company.entity.bean.formbean.FormBean;
import com.company.entity.bean.formbean.impl.LogInForm;
import com.company.populator.formbean.FormBeanPopulator;

import javax.servlet.http.HttpServletRequest;

public class LogInPopulator implements FormBeanPopulator {

    @Override
    public FormBean populate(HttpServletRequest req) {
        LogInForm bean = new LogInForm();

        bean.setLogin(req.getParameter("login"));
        bean.setPassword(req.getParameter("password"));

        return bean;
    }
}
