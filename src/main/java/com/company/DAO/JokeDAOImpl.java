package com.company.DAO;

import com.company.entity.Joke;
import com.company.enums.Statuses;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class JokeDAOImpl implements JokeDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Joke> list() {
        Query query = entityManager.createQuery("SELECT j FROM Joke j WHERE j.status = :status", Joke.class);
        query.setParameter("status", Statuses.NEW.getStatus());
        List<Joke> list = (List<Joke>) query.getResultList();
        Collections.reverse(list);
        return list;
    }


    @Override
    public List<Joke> listArchive() {
        Query query = entityManager.createQuery("SELECT j FROM Joke j WHERE j.status = :status", Joke.class);
        query.setParameter("status", Statuses.ARCHIVE.getStatus());
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
        }
    }


    @Override
    public void delete(int id) {
        try {
            entityManager.getTransaction().begin();
            Joke joke = entityManager.find(Joke.class, id);
            joke.setStatus(Statuses.DELETED.getStatus());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }



    @Override
    public void recover(int id) {
        try {
            entityManager.getTransaction().begin();
            Joke joke = entityManager.find(Joke.class, id);
            joke.setStatus(Statuses.DELETED.getStatus());
            Joke newJoke = new Joke(joke.getUser(), joke.getText());
            entityManager.persist(newJoke);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
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
        }
    }


    @Override
    public void dislike(int id){
        try {
            entityManager.getTransaction().begin();
            Joke joke = entityManager.find(Joke.class, id);
            int buff = joke.getDislikes()+1;

            if((joke.getLikes() + joke.getDislikes()) >= 10 && joke.getDislikes() > joke.getLikes())
                joke.setStatus(Statuses.ARCHIVE.getStatus());
            else
                joke.setDislikes(buff);

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
