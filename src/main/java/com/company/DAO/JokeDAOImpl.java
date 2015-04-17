package com.company.DAO;

import com.company.entity.Joke;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class JokeDAOImpl implements JokeDAO {

    @Autowired
    private EntityManager entityManager;

    /**
     * Возвращается массив со всеми активными Joke, которые должны быть выведены на главной странице.
     * @return
     */
    @Override
    public List<Joke> list() {
        Query query = entityManager.createQuery("SELECT j FROM Joke j WHERE j.mark = :mark", Joke.class);
        query.setParameter("mark", "new");
        List<Joke> list = (List<Joke>) query.getResultList();
        Collections.reverse(list);
        return list;
    }

    /**
     * Возвращается массив со всеми неудачными Joke, которые должны быть выведены на странице архива.
     * @return
     */
    @Override
    public List<Joke> listArchive() {
        Query query = entityManager.createQuery("SELECT j FROM Joke j WHERE j.mark = :mark", Joke.class);
        query.setParameter("mark", "archive");
        List<Joke> list = (List<Joke>) query.getResultList();
        Collections.reverse(list);
        return list;
    }

    /**
     * Метод добавляет переданную Joke.
     * @param joke
     */
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

    /**
     * Метод cтавит пометку deleted для joke, по выбраному joke.id.
     * @param id
     */
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

    /**
     * Метод восстанавливает Joke из "архива" по переданному id.
     * На деле старая Joke помечается как deleted.
     * Так сделано для того что бы обновить likes, dislikes и date у Joke. Помимо всего прочего голосования User-ов
     * завязаны на joke.id и так сделать проще.
     * @param id
     */
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

    /**
     * Метод добавляет один like к Joke, которая ищется по переданному id.
     * @param id
     */
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

    /**
     * Метод добавляет один dislike к Joke, которая ищется по переданному id.
     * Ежели шутка после этого становится негодной для показа на главной, ей добавляется пометка на перемещение в "архив".
     * Напомню логику и условия: шутка плохая если: (likes + dislikes >= 10) && (dislikes > likes)
     * @param id
     */
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
