package com.company.service.custom;

import com.company.DAO.JokeDAO;
import com.company.entity.Joke;
import com.company.entity.bean.formbean.impl.AddJokeForm;
import com.company.populator.entity.EntityFactory;
import com.company.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CustomJokeService implements JokeService{

    @Autowired
    private JokeDAO jokeDAO;

    @Autowired
    private EntityFactory entityFactory;

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
    public void addJoke(AddJokeForm formBean) {
        Joke joke = (Joke) entityFactory.create(Joke.class, formBean);
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
