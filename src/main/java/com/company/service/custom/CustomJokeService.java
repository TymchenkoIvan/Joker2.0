package com.company.service.custom;

import com.company.DAO.JokeDAO;
import com.company.DAO.StatusDAO;
import com.company.DAO.UserDAO;
import com.company.entity.Joke;
import com.company.entity.bean.dtobean.DTOBeans;
import com.company.entity.bean.dtobean.impl.JokeDTO;
import com.company.entity.bean.formbean.impl.JokeForm;
import com.company.enums.Statuses;
import com.company.populator.factory.DTOBeanFactory;
import com.company.populator.factory.EntityFactory;
import com.company.service.JokeService;
import com.company.util.ConfigParams;
import com.company.util.JokerTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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

    @Value(ConfigParams.JOKE_MIN_VOTES)
    private int minVotes;

    @Override
    @JokerTransaction
    public void addLike(Joke joke) {
        joke.setLikes(joke.getLikes() + 1);
        jokeDAO.persist(joke);
    }

    @Override
    @JokerTransaction
    public void deleteJoke(int jokeId) {
        Joke joke = jokeDAO.getJoke(jokeId);
        joke.setStatus(statusDAO.getStatus(Statuses.DELETED.getStatus()));
        jokeDAO.persist(joke);
    }

    @Override
    @JokerTransaction
    public void addDislike(Joke joke) {
        joke.setDislikes(joke.getDislikes() + 1);

        if(isMustBeArchived(joke))
            joke.setStatus(statusDAO.getStatus(Statuses.ARCHIVE.getStatus()));

        jokeDAO.persist(joke);
    }

    @Override
    @JokerTransaction
    public void addJoke(JokeForm formBean, String userLogin) {
        Joke joke = (Joke) entityFactory.create(Joke.class, formBean);
        joke.setStatus(statusDAO.getStatus(Statuses.NEW.getStatus()));
        joke.setUser(userDAO.getUserByLogin(userLogin));
        jokeDAO.persist(joke);
    }

    @Override
    @JokerTransaction
    public void recoverJokeFromArchive(int jokeId) {
        Joke oldJoke = jokeDAO.getJoke(jokeId);
        Joke newJoke = new Joke(oldJoke);

        oldJoke.setStatus(statusDAO.getStatus(Statuses.DELETED.getStatus()));
        newJoke.setStatus(statusDAO.getStatus(Statuses.NEW.getStatus()));

        jokeDAO.persist(oldJoke);
        jokeDAO.persist(newJoke);
    }

    @Override
    public JokeDTO getJokeById(int jokeId) {
        return (JokeDTO) dtoFactory.create(DTOBeans.JokeDTO, jokeDAO.getJoke(jokeId));
    }

    @Override
    public List<JokeDTO> getNewJokes() {
        return retrieveDtoFromJokes(jokeDAO.getByStatus(statusDAO.getStatus("new")));
    }

    @Override
    public List<JokeDTO> getArchivedJokes() {
        return retrieveDtoFromJokes(jokeDAO.getByStatus(statusDAO.getStatus(Statuses.ARCHIVE.getStatus())));
    }

    private List<JokeDTO> retrieveDtoFromJokes(List<Joke> jokes) {
        return jokes.stream()
                .map(joke -> (JokeDTO) dtoFactory.create(DTOBeans.JokeDTO, joke))
                .collect(Collectors.toList());
    }

    private boolean isMustBeArchived(Joke joke){
        return (joke.getLikes() + joke.getDislikes()) >= minVotes && joke.getDislikes() > joke.getLikes();
    }
}
