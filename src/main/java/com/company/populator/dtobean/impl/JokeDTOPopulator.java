package com.company.populator.dtobean.impl;

import com.company.entity.Entity;
import com.company.entity.Joke;
import com.company.entity.bean.dtobean.DTOBean;
import com.company.entity.bean.dtobean.impl.JokeDTO;
import com.company.populator.dtobean.DTOPopulator;

public class JokeDTOPopulator implements DTOPopulator{

    @Override
    public DTOBean populate(Entity entity) {
        Joke joke = (Joke) entity;
        JokeDTO jokeDTO = new JokeDTO();

        jokeDTO.setId(joke.getId());
        jokeDTO.setLikes(joke.getLikes());
        jokeDTO.setDislikes(joke.getDislikes());
        jokeDTO.setDate(joke.getDate());
        jokeDTO.setText(joke.getText());
        jokeDTO.setStatus(joke.getStatus().getStatus());
        jokeDTO.setUserLogin(joke.getUser().getLogin());

        return jokeDTO;
    }
}
