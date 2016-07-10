package com.company.config;

import com.company.DAO.JokeDAO;
import com.company.DAO.MySql.MySqlJokeDAO;
import com.company.DAO.MySql.MySqlUserDAO;
import com.company.DAO.UserDAO;
import com.company.service.JokeService;
import com.company.service.UserService;
import com.company.service.custom.CustomJokeService;
import com.company.service.custom.CustomUserService;
import com.company.validator.Validator;
import com.company.validator.formvalidator.AddJokeFormValidator;
import com.company.validator.formvalidator.LogInFormValidator;
import com.company.validator.formvalidator.SignUpFormValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@PropertySource("classpath:property/validator.properties")
public class AppConfig {

    @Bean
    public EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JokerJPA");
        return emf.createEntityManager();
    }

    @Bean
    public JokeDAO jokeDAO() {
        return new MySqlJokeDAO();
    }

    @Bean
    public UserDAO userDAO() {
        return new MySqlUserDAO();
    }

    @Bean
    public JokeService jokeService() {
        return new CustomJokeService();
    }

    @Bean
    public UserService userService() {
        return new CustomUserService();
    }

    @Bean
    public LogInFormValidator logInFormValidator() {
        return new LogInFormValidator();
    }

    @Bean
    public SignUpFormValidator signUpFormValidator() {
        return new SignUpFormValidator();
    }

    @Bean
    public AddJokeFormValidator addJokeFormValidator() {
        return new AddJokeFormValidator();
    }

    @Bean
    public Validator validator(){ return new Validator(); }

}
