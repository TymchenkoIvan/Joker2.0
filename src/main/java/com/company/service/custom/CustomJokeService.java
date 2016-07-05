package com.company.service.custom;

import com.company.DAO.JokeDAO;
import com.company.entity.Joke;
import com.company.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomJokeService implements JokeService{

    @Autowired
    private JokeDAO jokeDAO;

    @Override
    public void addLike(int jokeId) {
        jokeDAO.like(jokeId);
    }

    @Override
    public void deleteJoke(int jokeId) {
        jokeDAO.delete(jokeId);
    }

    @Override
    public void addDislike(int jokeId) {
        jokeDAO.dislike(jokeId);
    }

    @Override
    public void addJoke(String text) {
        Joke joke = new Joke(text);
        jokeDAO.add(joke);
    }

    @Override
    public void recoverJokeFromArchive(int jokeId) {
        jokeDAO.recover(jokeId);
    }

    @Override
    public List<Joke> getAllJokes() {
        return jokeDAO.list();
    }

    @Override
    public List<Joke> getArchivedJokes() {
        return jokeDAO.listArchive();
    }
}
