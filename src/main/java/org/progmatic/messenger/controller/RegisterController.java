package org.progmatic.messenger.controller;

import org.progmatic.messenger.service.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class RegisterController {

    String password;
    String userName;
    String email;
    String birth;

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
@Autowired
    public RegisterController( UserDetailsService inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = (InMemoryUserDetailsManager) inMemoryUserDetailsManager;
    }



    @RequestMapping(path="/register" ,method = RequestMethod.POST)
    public String register2 (@ModelAttribute("u2") MyUser user1){

        inMemoryUserDetailsManager.createUser(user1);       //  user1 nevu MyUser tipusu objektumot feltolti a weblap alapjan az input mezok ertekeivel. ezeket az u2 neven kapcsolja ossze
        System.out.println( userName + " Created!");
        return "/sajatlogin";
    }

    @RequestMapping(path="/register", method = RequestMethod.GET)
    public String register3 (@ModelAttribute("u2") MyUser user) {
        return "register";
    }

}
