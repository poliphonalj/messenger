/* admineknek megjelenik a user list menu es a torles gomb, usereknek nem*/




package org.progmatic.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
   // private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
  /*  @Autowired
    public LoginController( UserDetailsService inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = (InMemoryUserDetailsManager) inMemoryUserDetailsManager;
    }*/

    @RequestMapping(path = "/sajatlogin", method = RequestMethod.GET)
    public String login() {
        return "loginhtml";
    }
}








