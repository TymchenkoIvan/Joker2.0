package com.company.DAO.MySql;

import com.company.DAO.JokeDAO;
import com.company.entity.Joke;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class MySqlJokeDAO implements JokeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Joke> list() {
        Query query = entityManager.createQuery("SELECT j FROM Joke j WHERE j.mark = :mark", Joke.class);
        query.setParameter("mark", "new");
        List<Joke> list = (List<Joke>) query.getResultList();
        Collections.reverse(list);
        return list;
    }

    @Override
    public List<Joke> listArchive() {
        Query query = entityManager.createQuery("SELECT j FROM Joke j WHERE j.mark = :mark", Joke.class);
        query.setParameter("mark", "archive");
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
            joke.setMark("deleted");
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
            joke.setMark("deleted");
            Joke newJoke = new Joke(joke.getText());
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
                joke.setMark("archive");
            else
                joke.setDislikes(buff);

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
