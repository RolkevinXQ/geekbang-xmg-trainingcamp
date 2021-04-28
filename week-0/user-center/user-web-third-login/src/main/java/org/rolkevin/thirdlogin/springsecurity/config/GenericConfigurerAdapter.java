package org.rolkevin.thirdlogin.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 可以通过多实现GenericWebSecurityConfigurer，放在有序集合中，迭代使用
 */
public class GenericConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    GenericWebSecurityConfigurer webSecurityConfigurer;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        if (null != webSecurityConfigurer){
            webSecurityConfigurer.configure(http);
        }

    }
}
