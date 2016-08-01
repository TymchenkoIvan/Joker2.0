package com.company.service;

import com.company.entity.Joke;
import com.company.entity.bean.dtobean.impl.JokeDTO;
import com.company.entity.bean.formbean.impl.JokeForm;

import java.util.List;

public interface JokeService {

    void addLike(Joke joke);

    void addDislike(Joke joke);

    void deleteJoke(int jokeId);

    void addJoke(JokeForm formBean, String userLogin);

    void recoverJokeFromArchive(int jokeId);

    List<JokeDTO> getNewJokes();

    List<JokeDTO> getArchivedJokes();
}
