package org.rolkevin.thirdlogin.springsecurity.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface GenericWebSecurityConfigurer {

    default void configure(HttpSecurity http) throws Exception {

    }
}
