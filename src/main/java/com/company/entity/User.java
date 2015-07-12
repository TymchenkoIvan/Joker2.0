package com.company.entity;

import com.company.enums.Roles;

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

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "mail", nullable = false)
    private String mail;

    private String telephone;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Joke> writedJokes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="votes",
            joinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_joke", referencedColumnName = "id")}
    )
    private List<Joke> ratedJokes = new ArrayList<>();

    public User() {
    }

    public User(String login, String mail, String telephone, String password) {
        this.login = login;
        this.role = Roles.USER.getRole();
        this.mail = mail;
        if(!telephone.equals(null)) {
            this.telephone = telephone;
        }
        this.password = password;
    }

    public void addRatedJoke(Joke joke) {
        if ( ! ratedJokes.contains(joke))
            ratedJokes.add(joke);
        if ( ! joke.getUsersRates().contains(this))
            joke.getUsersRates().add(this);
    }

    public void addWritedJoke(Joke joke) {
        writedJokes.add(joke);
        if (joke.getUser() != this){
            joke.setUser(this);
        }
    }

    public List<Joke> getRatedJokes() {
        return ratedJokes;
    }

    public void setRatedJokes(List<Joke> jokes) {
        this.ratedJokes = jokes;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

