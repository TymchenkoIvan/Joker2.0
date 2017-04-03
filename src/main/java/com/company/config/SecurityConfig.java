package com.company.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public Md5PasswordEncoder encoder;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").failureUrl("/login").defaultSuccessUrl("/")
                .usernameParameter("login").passwordParameter("password")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/index").invalidateHttpSession(true)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/signup", "/faq", "/archive", "/", "/index", "/login**", "/resources/**", "/rest/**").permitAll()
                .antMatchers("/admin/**").access("hasRole('admin')")
                .antMatchers("/user/**", "/jokeForm").access("hasAnyRole('user', 'admin')")
                .anyRequest().authenticated();
    }
}
