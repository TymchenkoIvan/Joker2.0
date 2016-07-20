package com.company.service;

import com.company.entity.bean.dtobean.impl.JokeDTO;
import com.company.entity.bean.formbean.impl.JokeForm;

import java.util.List;

public interface JokeService {

    void addLike(int jokeId);

    void deleteJoke(int jokeId);

    void addDislike(int jokeId);

    void addJoke(JokeForm formBean, int userId);

    void recoverJokeFromArchive(int jokeId);

    List<JokeDTO> getNewJokes();

    List<JokeDTO> getArchivedJokes();
}
