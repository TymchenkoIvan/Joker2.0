package com.company.config;

import com.company.DAO.JokeDAO;
import com.company.DAO.JokeDAOImpl;
import com.company.DAO.UserDAO;
import com.company.DAO.UserDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@EnableWebMvc
public class AppConfig {

    @Bean
    public EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JokerJPA");
        return emf.createEntityManager();
    }

    @Bean
    public JokeDAO jokeDAO() {
        return new JokeDAOImpl();
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAOImpl();
    }
}
