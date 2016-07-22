package com.company.entity.bean.formbean.impl;

import com.company.entity.bean.formbean.FormBean;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpForm implements FormBean {

    @NotEmpty
    @Size(min=3, max = 15)
    @Pattern(regexp = "^[a-z0-9-]+$")
    private String login;

    @NotEmpty
    @Email
    private String mail;

    @Size(min=3, max = 15)
    @Pattern(regexp = "^[0-9-()]*$")
    private String telephone;

    @NotEmpty
    @Size(min=3, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9-]+$")
    private String password;

    @NotEmpty
    @Size(min=3, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9-]+$")
    private String confirm;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
