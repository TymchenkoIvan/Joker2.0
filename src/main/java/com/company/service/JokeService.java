package com.company.service;

import com.company.entity.Joke;

import java.util.List;

public interface JokeService {

    void addLike(int jokeId);

    void deleteJoke(int jokeId);

    void addDislike(int jokeId);

    void addJoke(String text);

    void recoverJokeFromArchive(int jokeId);

    List<Joke> getAllJokes();

    List<Joke> getArchivedJokes();
}
