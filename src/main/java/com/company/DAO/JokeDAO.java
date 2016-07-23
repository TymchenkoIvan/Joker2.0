package com.company.DAO;

import com.company.entity.Joke;
import com.company.entity.Status;

import java.util.List;

public interface JokeDAO {

    Joke getJoke(int id);

    List<Joke> getByStatus(Status status);

    void persist(Joke joke);
}