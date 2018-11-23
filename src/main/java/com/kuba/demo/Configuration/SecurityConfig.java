package com.kuba.demo.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/main").authenticated()
                .antMatchers("/admin").hasAnyRole("ADMIN", "SUPERADMIN")
                .antMatchers("/superadmin").hasRole("SUPERADMIN")
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/loginhere")
                .and().logout().logoutSuccessUrl("/")
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/denied");
    }
}
