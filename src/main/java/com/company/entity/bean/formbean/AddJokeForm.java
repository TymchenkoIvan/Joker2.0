package com.company.entity.bean.formbean;

import com.company.entity.bean.Bean;

public class AddJokeForm implements Bean {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
