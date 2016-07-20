package com.company.populator.entity.impl;

import com.company.entity.Entity;
import com.company.entity.Joke;
import com.company.entity.bean.formbean.FormBean;
import com.company.entity.bean.formbean.impl.JokeForm;
import com.company.populator.entity.EntityPopulator;

import java.util.Date;

public class JokePopulator implements EntityPopulator {

    @Override
    public Entity populate(FormBean formBean) {
        JokeForm form = (JokeForm)formBean;
        Joke joke = new Joke();

        joke.setText(form.getText());
        joke.setDate(new Date());

        return joke;
    }
}
