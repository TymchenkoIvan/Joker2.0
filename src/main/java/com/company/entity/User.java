package com.company.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "users")
public class User extends Entity {

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String mail;

    private String telephone;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy= "user", fetch= FetchType.LAZY)
    private Set<Joke> jokes = new HashSet<Joke>();

    @OneToMany(mappedBy="user", fetch= FetchType.LAZY)
    private List<Vote> votes = new ArrayList<Vote>();

    public User() {
    }

    public User(String login, String mail, String password) {
        this.login = login;
        this.mail = mail;
        this.telephone = null;
        this.password = password;
    }

    public Set<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(Set<Joke> myJokes) {
        this.jokes = myJokes;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role){
        this.role = role;
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

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
