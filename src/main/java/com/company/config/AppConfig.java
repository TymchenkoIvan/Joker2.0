package com.company.config;

import com.company.service.JokeService;
import com.company.service.UserService;
import com.company.service.VoteService;
import com.company.service.custom.CustomJokeService;
import com.company.service.custom.CustomUserService;
import com.company.service.custom.CustomVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Autowired
    protected Environment props;

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
