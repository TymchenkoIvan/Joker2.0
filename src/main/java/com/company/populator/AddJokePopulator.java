package com.company.populator;

import com.company.entity.bean.Bean;
import com.company.entity.bean.formbean.AddJokeForm;

import javax.servlet.http.HttpServletRequest;

public class AddJokePopulator implements BeanPopulator {

    @Override
    public Bean populate(HttpServletRequest req) {
        AddJokeForm bean = new AddJokeForm();

        bean.setText(req.getParameter("text"));

        return bean;
    }
}
