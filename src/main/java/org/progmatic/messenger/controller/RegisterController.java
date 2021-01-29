package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.MyUser;
import org.progmatic.messenger.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
public class RegisterController {
    @PersistenceContext
    EntityManager em;


    String password;
    String userName;
    String email;
    String birth;
    //private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private MyUserDetailService us;

    @Autowired
    public RegisterController(MyUserDetailService us) {
        this.us = us;
    }

    @Transactional
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register2(@ModelAttribute("u2") MyUser user1) {

        us.addUser(user1);       //  user1 nevu MyUser tipusu objektumot feltolti a weblap alapjan az input mezok ertekeivel. ezeket az u2 neven kapcsolja ossze
        return "redirect:/message/addMessage";
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register3(@ModelAttribute("u2") MyUser user) {
        return "register";
    }
}
