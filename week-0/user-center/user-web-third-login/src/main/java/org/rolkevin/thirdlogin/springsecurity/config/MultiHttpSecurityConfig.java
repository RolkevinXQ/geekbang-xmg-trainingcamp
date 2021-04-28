package org.rolkevin.thirdlogin.springsecurity.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MultiHttpSecurityConfig {

    @Configuration
    public static class SecurityConfig1 extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            System.out.println("自定义SecurityConfig1....");
            http.csrf().disable().authorizeRequests()
                .antMatchers("/test/**")
                .authenticated().anyRequest().permitAll();
            //super.configure(http);
        }
    }

    @Configuration
    @Order(101)
    public static class SecurityConfig2 extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            System.out.println("自定义SecurityConfig2....");
            http.antMatcher("/test/**")
            .authorizeRequests()
                    .anyRequest()
                    .authenticated();
            //super.configure(http);
        }
    }
}
