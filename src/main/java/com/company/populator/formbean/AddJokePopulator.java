package com.company.populator.formbean;

import com.company.entity.bean.formbean.FormBean;
import com.company.entity.bean.formbean.impl.AddJokeForm;

import javax.servlet.http.HttpServletRequest;

public class AddJokePopulator implements FormBeanPopulator {

    @Override
    public FormBean populate(HttpServletRequest req) {
        AddJokeForm bean = new AddJokeForm();

        bean.setText(req.getParameter("text"));

        return bean;
    }
}
