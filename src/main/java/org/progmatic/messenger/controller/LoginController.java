package org.progmatic.messenger.controller;

import org.progmatic.messenger.service.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
UserDetailsService us;
InMemoryUserDetailsManager ius;

    @Autowired
    public LoginController( UserDetailsService us) {
        this.us=us;
        this.ius=(InMemoryUserDetailsManager )us;


    }

    @RequestMapping(path = "/sajatlogin", method = RequestMethod.GET)
    public String login() {



        return "/loginhtml";
    }












    }








