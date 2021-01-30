package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.model.MyUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("loggedName", currentPrincipalName);

        model.addAttribute("userslist",users);
        return "messagesByUser";
    }

    @Transactional  //itt valasztja ki a userekhez tartozo uzeneteket, es beteszi oket egy
    @RequestMapping(path = "/message/msgByUsrToList", method = RequestMethod.POST)
    public String msgByUsr2(@ModelAttribute("msgbyusr")  MyUser user,Model model) {
       List<Message>messages= em.createQuery("SELECT m From Message m WHERE m.user.userId=:s").setParameter("s",user.getUserId()).getResultList();

        System.out.println("kiscica"+user.getUserId());
        for (Message message : messages) {
            System.out.println(message.getText());
            System.out.println("nagycica"+user.getUserId());
        }
        System.out.println();
        model.addAttribute("messages",messages);
        return "messagesByUser";
    }


}
