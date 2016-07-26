package com.company.service.custom;

import com.company.DAO.JokeDAO;
import com.company.DAO.StatusDAO;
import com.company.DAO.UserDAO;
import com.company.entity.Joke;
import com.company.entity.bean.dtobean.DTOBeans;
import com.company.entity.bean.dtobean.impl.JokeDTO;
import com.company.entity.bean.formbean.impl.JokeForm;
import com.company.populator.factory.DTOBeanFactory;
import com.company.populator.factory.EntityFactory;
import com.company.service.JokeService;
import com.company.util.ConfigParam;
import com.company.util.JokerTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.stream.Collectors;

public class CustomJokeService implements JokeService{

    @Autowired
    protected Environment props;

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
    @JokerTransaction
    public void addLike(Joke joke) {
        joke.setLikes(joke.getLikes() + 1);
        jokeDAO.persist(joke);
    }

    @Override
    @JokerTransaction
    public void deleteJoke(int jokeId) {
        Joke joke = jokeDAO.getJoke(jokeId);
        joke.setStatus(statusDAO.getStatus("deleted"));
        jokeDAO.persist(joke);
    }

    @Override
    @JokerTransaction
    public void addDislike(Joke joke) {
        joke.setDislikes(joke.getDislikes() + 1);

        if(isMustBeArchived(joke))
            joke.setStatus(statusDAO.getStatus("archive"));

        jokeDAO.persist(joke);
    }

    @Override
    @JokerTransaction
    public void addJoke(JokeForm formBean, int userId) {
        Joke joke = (Joke) entityFactory.create(Joke.class, formBean);
        joke.setStatus(statusDAO.getStatus("new"));
        joke.setUser(userDAO.getUserById(userId));
        jokeDAO.persist(joke);
    }

    @Override
    @JokerTransaction
    public void recoverJokeFromArchive(int jokeId) {
        Joke oldJoke = jokeDAO.getJoke(jokeId);
        Joke newJoke = new Joke(oldJoke);

        oldJoke.setStatus(statusDAO.getStatus("deleted"));
        newJoke.setStatus(statusDAO.getStatus("new"));

        jokeDAO.persist(oldJoke);
        jokeDAO.persist(newJoke);
    }

    @Override
    public List<JokeDTO> getNewJokes() {
        return retrieveDtoFromJokes(jokeDAO.getByStatus(statusDAO.getStatus("new")));
    }

    @Override
    public List<JokeDTO> getArchivedJokes() {
        return retrieveDtoFromJokes(jokeDAO.getByStatus(statusDAO.getStatus("archive")));
    }

    private List<JokeDTO> retrieveDtoFromJokes(List<Joke> jokes) {
        return jokes.stream()
                .map(joke -> (JokeDTO) dtoFactory.create(DTOBeans.JokeDTO, joke))
                .collect(Collectors.toList());
    }

    private boolean isMustBeArchived(Joke joke){
        int minVotes = Integer.parseInt(props.getProperty(ConfigParam.JOKE_ARCHIVE_MIN_VOTES));
        return (joke.getLikes() + joke.getDislikes()) >= minVotes && joke.getDislikes() > joke.getLikes();
    }
}
