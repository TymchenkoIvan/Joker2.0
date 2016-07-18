package com.company.service;

public interface VoteService {

    void addVote(int jokeId, int userId);

    boolean isVotePossible(int jokeId, int userId);
}
