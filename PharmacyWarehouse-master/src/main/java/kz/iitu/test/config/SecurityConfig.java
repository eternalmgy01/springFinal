package kz.iitu.test.config;

import kz.iitu.test.service.FirmService;
import kz.iitu.test.service.impl.FirmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private FirmServiceImpl firmService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().
                disable().
                authorizeRequests().
                antMatchers("/auth/**").permitAll().
                antMatchers("/firms/register/**").permitAll().
                antMatchers("/firms/registerManager/**").hasAnyAuthority("ADMIN").
                antMatchers("/medicines/list").permitAll().
                antMatchers("/medicines/**").hasAnyAuthority("ADMIN", "MANAGER").
                antMatchers("/requests/make").hasAuthority("USER").
                antMatchers("/requests/**").hasAnyAuthority("ADMIN", "MANAGER").
                antMatchers("/firms/**").hasAuthority("ADMIN").
                and().addFilter(new JwtTokenGeneratorFilter(authenticationManager())).
                addFilterAfter(new JwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(firmService).passwordEncoder(passwordEncoder());
    }
}
