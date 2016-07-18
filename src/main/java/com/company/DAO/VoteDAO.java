package com.company.DAO;

import com.company.entity.Joke;
import com.company.entity.User;

public interface VoteDAO {

    void addVote(Joke joke, User user);

    boolean isVotePossible(int jokeId, int userId);
}
