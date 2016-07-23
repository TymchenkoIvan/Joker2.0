package com.company.DAO.Jpa;

import com.company.DAO.VoteDAO;
import com.company.entity.Joke;
import com.company.entity.User;
import com.company.entity.Vote;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class JpaVoteDAO implements VoteDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addVote(Joke joke, User user) {
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setJoke(joke);

        entityManager.persist(vote);
    }

    @Override
    public boolean isVotePossible(int jokeId, int userId) {
        Query query = entityManager.createQuery("SELECT v FROM Vote v WHERE v.user.id = :userId AND v.joke.id = :jokeId", Vote.class);
        query.setParameter("userId", userId);
        query.setParameter("jokeId", jokeId);

        return query.getResultList().size() == 0;
    }
}
