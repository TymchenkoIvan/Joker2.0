package com.company.entity.bean.formbean.impl;

import com.company.entity.bean.formbean.FormBean;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LogInForm implements FormBean {

    @NotEmpty
    @Size(min=3, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9-]+$")
    private String login;

    @NotEmpty
    @Size(min=3, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9-]+$")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
