package com.company.service;

public interface VoteService {

    boolean isVotePossible(int jokeId, String userLogin);

    void addLike(int jokeId, String userLogin);

    void addDislike(int jokeId, String userLogin);
}
