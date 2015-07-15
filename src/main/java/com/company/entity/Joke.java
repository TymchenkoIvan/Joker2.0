package com.company.entity;

import com.company.config.Constants;
import com.company.enums.Statuses;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = Constants.SQL_TABLE_JOKES)
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private int likes;

    @Column(nullable = false)
    private int dislikes;

    @Column(nullable = false)
    private String text;

    @ManyToMany(mappedBy= "ratedJokes")
    private List<User> usersRates = new ArrayList<>();

    public Joke() {}

    public Joke(User user, String text) {
        this.user = user;
        this.status = Statuses.NEW.getStatus();
        this.date = new Date();
        this.likes = 0;
        this.dislikes = 0;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<User> getUsersRates() {
        return usersRates;
    }

    public void setUsersRates(List<User> usersRates) {
        this.usersRates = usersRates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joke)) return false;

        Joke joke = (Joke) o;

        if (dislikes != joke.dislikes) return false;
        if (id != joke.id) return false;
        if (likes != joke.likes) return false;
        if (!date.equals(joke.date)) return false;
        if (!status.equals(joke.status)) return false;
        if (!text.equals(joke.text)) return false;
        if (!user.equals(joke.user)) return false;
        if (usersRates != null ? !usersRates.equals(joke.usersRates) : joke.usersRates != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + likes;
        result = 31 * result + dislikes;
        result = 31 * result + text.hashCode();
        result = 31 * result + (usersRates != null ? usersRates.hashCode() : 0);
        return result;
    }
}