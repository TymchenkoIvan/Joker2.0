package com.company.DAO.Jpa;

import com.company.DAO.JokeDAO;
import com.company.DAO.StatusDAO;
import com.company.entity.Joke;
import com.company.entity.Status;
import com.company.exception.JokerDBException;
import com.company.util.ConfigParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class JpaJokeDAO implements JokeDAO {

    @Autowired
    protected Environment props;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
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
    public void add(Joke joke) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(joke);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
            throw new JokerDBException(ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            entityManager.getTransaction().begin();
            Joke joke = entityManager.find(Joke.class, id);
            joke.setStatus(statusDAO.getStatus("deleted"));
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
            throw new JokerDBException(ex);
        }
    }

    @Override
    public void recover(int id) {
        try {
            entityManager.getTransaction().begin();
            Joke joke = entityManager.find(Joke.class, id);
            joke.setStatus(statusDAO.getStatus("deleted"));
            Joke newJoke = new Joke(joke.getText());
            entityManager.persist(newJoke);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
            throw new JokerDBException(ex);
        }
    }

    @Override
    public void like(int id){
        try {
            entityManager.getTransaction().begin();
            Joke joke = entityManager.find(Joke.class, id);
            int buff = joke.getLikes()+1;
            joke.setLikes(buff);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
            throw new JokerDBException(ex);
        }
    }

    @Override
    public void dislike(int id){
        int minVotes = Integer.parseInt(props.getProperty(ConfigParam.JOKE_ARCHIVE_MIN_VOTES));
        try {
            entityManager.getTransaction().begin();
            Joke joke = entityManager.find(Joke.class, id);
            int buff = joke.getDislikes()+1;

            if((joke.getLikes() + joke.getDislikes()) >= minVotes && joke.getDislikes() > joke.getLikes())
                joke.setStatus(statusDAO.getStatus("archive"));
            else
                joke.setDislikes(buff);

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
            throw new JokerDBException(ex);
        }
    }
}