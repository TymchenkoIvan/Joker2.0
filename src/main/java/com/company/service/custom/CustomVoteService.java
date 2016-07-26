package com.company.service.custom;

import com.company.DAO.JokeDAO;
import com.company.DAO.UserDAO;
import com.company.DAO.VoteDAO;
import com.company.entity.Joke;
import com.company.entity.User;
import com.company.service.JokeService;
import com.company.service.VoteService;
import com.company.util.JokerTransaction;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomVoteService implements VoteService{

    @Autowired
    private VoteDAO voteDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JokeDAO jokeDAO;

    @Autowired
    private JokeService jokeService;

    @Override
    public boolean isVotePossible(int jokeId, int userId) {
        return voteDAO.isVotePossible(jokeId, userId);
    }

    @Override
    @JokerTransaction
    public void addLike(int jokeId, int userId) {
        User user = userDAO.getUserById(userId);
        Joke joke = jokeDAO.getJoke(jokeId);

        voteDAO.addVote(joke, user);
        jokeService.addLike(joke);
    }

    @Override
    @JokerTransaction
    public void addDislike(int jokeId, int userId) {
        User user = userDAO.getUserById(userId);
        Joke joke = jokeDAO.getJoke(jokeId);

        voteDAO.addVote(joke, user);
        jokeService.addDislike(joke);
    }
}
