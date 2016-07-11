package com.company.entity.bean.dtobean.impl;

import com.company.entity.bean.dtobean.DTOBean;

public class UserDTO implements DTOBean{
    private int id;

    private String login;

    private String mail;

    private String mark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
