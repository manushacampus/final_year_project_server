package com.bit.final_project.security;

import com.bit.final_project.security.filters.ExceptionHandlerFilter;
import com.bit.final_project.security.filters.jwt.JWTAuthEntryHandler;
import com.bit.final_project.security.filters.jwt.JWTSecurityFilter;
import com.bit.final_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/user/**","/api/file/{type}/{fileName}","/api/forget-password").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .and()
                    .antMatcher("/api/**").exceptionHandling()
                    .authenticationEntryPoint(new JWTAuthEntryHandler())
                    .and()
                    .addFilter(new JWTSecurityFilter(authenticationManager(),this.userService))
                    .addFilterBefore(new ExceptionHandlerFilter(),JWTSecurityFilter.class);
        http.cors();
    }
}
