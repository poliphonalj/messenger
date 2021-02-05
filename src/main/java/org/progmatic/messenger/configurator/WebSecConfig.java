package org.progmatic.messenger.configurator;

import org.progmatic.messenger.service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                .loginProcessingUrl("/sajatlogin").permitAll()
                .loginPage("/sajatlogin").permitAll()
                .usernameParameter("userName")
                .passwordParameter("password").failureForwardUrl("/sajatlogin")
                .and().
                logout().logoutSuccessUrl("/sajatlogin").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/register").permitAll()    //mindenki johet ide=aki nem jelentkezett be az csak ezt az ablakot latja
                .antMatchers("/error").permitAll()
                .antMatchers("/rest/listmessages").permitAll()
                .antMatchers("/rest/csrf").permitAll()
                .antMatchers("/rest/message").permitAll()
                .antMatchers("/messagesByUser").permitAll()
                .anyRequest().authenticated();
    }
}