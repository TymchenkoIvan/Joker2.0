package com.company.config;


import com.company.DAO.*;
import com.company.DAO.Jpa.*;
import com.company.util.ConfigParam;
import com.company.util.Convertor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class PersistenceConfig extends AppConfig{

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory(props.getProperty(ConfigParam.ENTITY_MANAGER));
    }

    @Bean
    public Convertor convertor(){
        return new Convertor();
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    public VoteDAO voteDAO() {
        return new JpaVoteDAO();
    }

    @Bean
    public RoleDAO roleDAO() {
        return new JpaRoleDAO();
    }

    @Bean
    public StatusDAO statusDAO() {
        return new JpaStatusDAO();
    }

    @Bean
    public JokeDAO jokeDAO() {
        return new JpaJokeDAO();
    }

    @Bean
    public UserDAO userDAO() {
        return new JpaUserDAO();
    }
}
