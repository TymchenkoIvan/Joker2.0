package com.company.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "jokes")
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "likes", nullable = false)
    private int likes;

    @Column(name = "dislikes", nullable = false)
    private int dislikes;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "mark", nullable = false)
    private String mark;

    @ManyToMany(mappedBy="jokes")
    private List<User> users = new ArrayList<User>();

    public Joke() {}

    public Joke(String text) {
        this.date = new Date();
        this.likes = 0;
        this.dislikes = 0;
        this.text = text;
        this.mark = "new";
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}