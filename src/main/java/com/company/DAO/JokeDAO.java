package com.company.DAO;

import com.company.entity.Joke;

import java.util.List;

public interface JokeDAO {
	List<Joke> list();
    List<Joke> listArchive();
	void add(Joke joke);
    void delete(int id);
    void recover(int id);
    void like(int id);
    void dislike(int id);
}
