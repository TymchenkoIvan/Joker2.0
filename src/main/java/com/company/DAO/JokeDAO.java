package com.company.DAO;

import com.company.entity.Joke;
import com.company.entity.Status;

import java.util.List;

public interface JokeDAO {

    Joke getJoke(int id);

    List<Joke> getByStatus(Status status);

    void add(Joke joke);

    void delete(int id);

    void recover(int id);

    void like(int id);

    void dislike(int id);
}