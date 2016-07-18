package com.company.DAO.Jpa;

import com.company.DAO.VoteDAO;
import com.company.entity.Joke;
import com.company.entity.User;
import com.company.entity.Vote;
import com.company.exception.JokerDBException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaVoteDAO implements VoteDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void addVote(Joke joke, User user) {
        try {
            entityManager.getTransaction().begin();
            Vote vote = new Vote();
            vote.setUser(user);
            vote.setJoke(joke);
            entityManager.persist(vote);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
            throw new JokerDBException(ex);
        }
    }

    @Override
    public boolean isVotePossible(int jokeId, int userId) {
        Query query = entityManager.createQuery("SELECT v FROM Vote v WHERE v.user.id = :userId AND v.joke.id = :jokeId", Vote.class);
        query.setParameter("userId", userId);
        query.setParameter("jokeId", jokeId);

        return query.getResultList().size() == 0;
    }
}
