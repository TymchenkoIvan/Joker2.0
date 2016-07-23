package com.company.config;

import com.company.DAO.*;
import com.company.DAO.Jpa.*;
import com.company.service.JokeService;
import com.company.service.UserService;
import com.company.service.VoteService;
import com.company.service.custom.CustomJokeService;
import com.company.service.custom.CustomUserService;
import com.company.service.custom.CustomVoteService;
import com.company.util.ConfigParam;
import com.company.util.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Autowired
    protected Environment props;

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

    @Bean
    public VoteService voteService() {
        return new CustomVoteService();
    }

    @Bean
    public JokeService jokeService() {
        return new CustomJokeService();
    }

    @Bean
    public UserService userService() {
        return new CustomUserService();
    }
}
