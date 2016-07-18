package com.company.service.custom;

import com.company.DAO.JokeDAO;
import com.company.DAO.StatusDAO;
import com.company.DAO.UserDAO;
import com.company.entity.Joke;
import com.company.entity.bean.dtobean.DTOBeans;
import com.company.entity.bean.dtobean.impl.JokeDTO;
import com.company.entity.bean.formbean.impl.AddJokeForm;
import com.company.populator.factory.DTOBeanFactory;
import com.company.populator.factory.EntityFactory;
import com.company.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CustomJokeService implements JokeService{

    @Autowired
    private JokeDAO jokeDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private EntityFactory entityFactory;

    @Autowired
    private DTOBeanFactory dtoFactory;

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
    public void addJoke(AddJokeForm formBean, int userId) {
        Joke joke = (Joke) entityFactory.create(Joke.class, formBean);
        joke.setStatus(statusDAO.getStatus("new"));
        joke.setUser(userDAO.getUserById(userId));
        jokeDAO.add(joke);
    }

    @Override
    public void recoverJokeFromArchive(int jokeId) {
        jokeDAO.recover(jokeId);
    }

    @Override
    public List<JokeDTO> getNewJokes() {
        return retrieveDtoFromJokes(jokeDAO.getByStatus(statusDAO.getStatus("new")));
    }

    private List<JokeDTO> retrieveDtoFromJokes(List<Joke> jokes) {
        return jokes.stream()
                .map(joke -> (JokeDTO) dtoFactory.create(DTOBeans.JokeDTO, joke))
                .collect(Collectors.toList());
    }

    @Override
    public List<JokeDTO> getArchivedJokes() {
        return retrieveDtoFromJokes(jokeDAO.getByStatus(statusDAO.getStatus("archive")));
    }
}
