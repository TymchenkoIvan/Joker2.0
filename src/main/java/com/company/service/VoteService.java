package com.company.service;

public interface VoteService {

    boolean isVotePossible(int jokeId, int userId);

    void addLike(int jokeId, int userId);

    void addDislike(int jokeId, int userId);
}
