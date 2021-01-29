package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.MyUser;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
public class UserListController {
    @PersistenceContext
    EntityManager em;


    @Transactional
    @RequestMapping(path = "/message/userList", method = RequestMethod.GET)
    public String userList(Model model) {

        System.out.println(( em.createQuery("SELECT my FROM MyUser my")).getResultList().size());
       model.addAttribute("listOfUsers", em.createQuery("SELECT my FROM MyUser my").getResultList());
        return "userList";
    }



}
