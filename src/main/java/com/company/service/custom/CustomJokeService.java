package com.company.service.custom;

import com.company.DAO.JokeDAO;
import com.company.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomJokeService implements JokeService{

    @Autowired
    private JokeDAO jokeDAO;
}
