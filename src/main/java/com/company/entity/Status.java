package com.company.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "statuses")
public class Status extends Entity {

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy="status", fetch= FetchType.LAZY)
    private List<Joke> jokes = new ArrayList<Joke>();

    public Status() {
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "statuses")
    public List<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
