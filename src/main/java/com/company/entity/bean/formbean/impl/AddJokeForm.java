package com.company.entity.bean.formbean.impl;

import com.company.entity.bean.formbean.FormBean;

public class AddJokeForm implements FormBean {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
