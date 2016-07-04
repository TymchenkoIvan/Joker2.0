package com.company.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "mail", nullable = false)
    private String mail;

    private String telephone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "mark")
    private String mark;

    @ManyToMany
    @JoinTable(
            name="votes",
            joinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_joke", referencedColumnName = "id")}
    )
    private List<Joke> jokes = new ArrayList<>();

    public User() {
    }

    public User(String login, String mail, String password) {
        this.login = login;
        this.mail = mail;
        this.telephone = null;
        this.password = password;
        this.mark = null;
    }

    public void addJoke(Joke joke) {
        if ( ! jokes.contains(joke))
            jokes.add(joke);
        if ( ! joke.getUsers().contains(this))
            joke.getUsers().add(this);
    }

    public List<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }

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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}

