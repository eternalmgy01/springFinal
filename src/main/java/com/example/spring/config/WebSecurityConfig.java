package com.example.spring.config;

import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/users/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/games/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/games/**", "/addresses/**").hasAnyAuthority( "ADMIN")
                .antMatchers(HttpMethod.PUT,"/users/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/games/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/users/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/games/**").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/users/signUp").permitAll()
                .antMatchers("/api/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JwtTokenGeneratorFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }
}
