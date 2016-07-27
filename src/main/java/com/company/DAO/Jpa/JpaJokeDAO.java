package com.company.DAO.Jpa;

import com.company.DAO.JokeDAO;
import com.company.entity.Joke;
import com.company.entity.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class JpaJokeDAO implements JokeDAO {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Joke getJoke(int id) {
        Query query = entityManager.createQuery("SELECT j FROM Joke j WHERE j.id = :id", Joke.class);
        query.setParameter("id", id);

        return (Joke)query.getSingleResult();
    }

    @Override
    public List<Joke> getByStatus(Status status) {
        Query query = entityManager.createQuery("SELECT j FROM Joke j WHERE j.status.status = :status", Joke.class);
        query.setParameter("status", status.getStatus());
        List<Joke> list = (List<Joke>) query.getResultList();
        Collections.reverse(list);
        return list;
    }

    @Override
    public void persist(Joke joke) {
        entityManager.persist(joke);
    }
}