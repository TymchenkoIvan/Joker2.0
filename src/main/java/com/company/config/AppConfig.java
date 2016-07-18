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
import com.company.validator.Validator;
import com.company.validator.formvalidator.AddJokeFormValidator;
import com.company.validator.formvalidator.LogInFormValidator;
import com.company.validator.formvalidator.SignUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@PropertySources({
        @PropertySource("classpath:validator.properties"),
        @PropertySource("classpath:config.properties")})
public class AppConfig {

    @Autowired
    protected Environment props;

    @Bean
    public EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(props.getProperty(ConfigParam.ENTITY_MANAGER));
        return emf.createEntityManager();
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
