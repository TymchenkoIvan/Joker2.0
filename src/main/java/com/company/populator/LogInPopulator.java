package com.company.populator;

import com.company.entity.bean.Bean;
import com.company.entity.bean.formbean.LogInForm;

import javax.servlet.http.HttpServletRequest;

public class LogInPopulator implements BeanPopulator {

    @Override
    public Bean populate(HttpServletRequest req) {
        LogInForm bean = new LogInForm();

        bean.setLogin(req.getParameter("login"));
        bean.setPassword(req.getParameter("password"));

        return bean;
    }
}
