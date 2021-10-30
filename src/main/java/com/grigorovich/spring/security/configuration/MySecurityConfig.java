package com.grigorovich.spring.security.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@EnableWebSecurity //эта аннотация явялется конфигурацией можно configuration не писать
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder userBuilder= User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("Veronika").password("Veronika").roles("EMPLOYEE"))
                .withUser(userBuilder.username("Elena").password("Elena").roles("HR"))
                .withUser(userBuilder.username("Ivan").password("Ivan").roles("MANAGER", "HR"));
    }

    @Override //делаем авторизацию
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("EMPLOYEE","HR", "MANAGER")
                .antMatchers("/hr_info").hasRole("HR")
                .antMatchers("/manager_info").hasRole("MANAGER") //"/manager_info/**" это значит что все другие страницы у которых впереди manager_info будут видны только менеджерам
                .and().formLogin().permitAll();
    }
}
