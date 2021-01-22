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


    @RequestMapping(path = "/sajatlogin", method = RequestMethod.GET)
    public String login(@ModelAttribute("u1") MyUser user) {
        return "/loginhtml";
    }

}








