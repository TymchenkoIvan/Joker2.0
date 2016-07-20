package com.company.entity.bean.formbean.impl;

import com.company.entity.bean.formbean.FormBean;

import javax.validation.constraints.Size;

public class JokeForm implements FormBean {

    @Size(min=3)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
