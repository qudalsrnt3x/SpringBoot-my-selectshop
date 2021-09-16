package com.sparta.myselectshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/css/**","/images/**", "/h2-console/**", "/user/**").permitAll()
                .anyRequest().authenticated()

                .and()

                .formLogin()
                .loginPage("/user/login")
                .failureUrl("/user/login/error")
                .defaultSuccessUrl("/")
                .permitAll()

                .and()

                .logout()
                .logoutUrl("/user/logout")
                .permitAll();
    }
}
