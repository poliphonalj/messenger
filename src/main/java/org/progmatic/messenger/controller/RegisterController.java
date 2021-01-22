package org.progmatic.messenger.controller;

import org.progmatic.messenger.service.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
@Autowired
    public RegisterController( UserDetailsService inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = (InMemoryUserDetailsManager) inMemoryUserDetailsManager;
    }



    @RequestMapping(path="/register" ,method = RequestMethod.POST)
    public String register2 (@ModelAttribute("u2") @RequestParam("userName") String userName, @RequestParam("email") String email, @RequestParam("birth") String birth, @RequestParam("password") String password) {

        inMemoryUserDetailsManager.createUser(org.springframework.security.core.userdetails.User.withUsername(userName).password(password).roles("USER")
       .build());




        System.out.println( userName + " Created!");

        return "/sajatlogin";
    }

    @RequestMapping(path="/register", method = RequestMethod.GET)
    public String register3 (@ModelAttribute("u2") MyUser user) {

        return "register";
    }


}
