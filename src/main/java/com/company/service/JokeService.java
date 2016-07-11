package com.company.service;

import com.company.entity.bean.dtobean.impl.JokeDTO;
import com.company.entity.bean.formbean.impl.AddJokeForm;

import java.util.List;

public interface JokeService {

    void addLike(int jokeId);

    void deleteJoke(int jokeId);

    void addDislike(int jokeId);

    void addJoke(AddJokeForm formBean);

    void recoverJokeFromArchive(int jokeId);

    List<JokeDTO> getAllJokes();

    List<JokeDTO> getArchivedJokes();
}
