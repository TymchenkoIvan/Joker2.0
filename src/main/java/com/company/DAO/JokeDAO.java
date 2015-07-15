package com.company.DAO;

import com.company.Exceptions.JokerAppException;
import com.company.Exceptions.JokerDBException;
import com.company.entity.Joke;
import com.company.enums.Statuses;

import java.util.List;

public interface JokeDAO {
    List<Joke> list(Statuses status, int page);
    List<Joke> listArchive();
	void add(Joke joke) throws JokerAppException;
    void delete(int id) throws JokerDBException;
    void recover(int id) throws JokerDBException;
    void like(int id) throws JokerDBException;
    void dislike(int id) throws JokerDBException;
    int jokesCountByStatus(Statuses status);
}
