package org.progmatic.messenger.configurator;

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
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").
                password("p").roles("ADMIN").build());
        return manager;
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                .loginProcessingUrl("/sajatlogin")
                .loginPage("/sajatlogin").permitAll()
                .usernameParameter("userName")
                .passwordParameter("password")
                .and()
                .authorizeRequests()
                .antMatchers("/register", "/").permitAll()    //mindenki johet ide=aki nem jelentkezett be az csak ezt az ablakot latja
                .anyRequest().authenticated();
    }
}