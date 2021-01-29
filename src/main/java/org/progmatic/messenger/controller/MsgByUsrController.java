package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.model.MyUser;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Controller
public class MsgByUsrController {
    @PersistenceContext
    EntityManager em;

    @RequestMapping(path = "/message/msgByUsr", method = RequestMethod.GET)
    public String msgByUsr(@ModelAttribute("msgbyusr") MyUser user,Model model) {


        List<MyUser> users= em.createQuery("SELECT my FROM MyUser my").getResultList();

        System.out.println(users.size());
        System.out.println();
        model.addAttribute("userslist",users);
        return "messagesByUser";
    }

    @Transactional
    @RequestMapping(path = "/message/msgByUsrToList", method = RequestMethod.POST)
    public String msgByUsr2(@ModelAttribute("msgbyusr")  MyUser user,Model model) {


        System.out.println("kiscica"+user.getUserId());
        return "messagesByUser";
    }


}
