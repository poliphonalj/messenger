package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.Message;

import org.progmatic.messenger.model.SearchEntity;
import org.progmatic.messenger.service.MessageService;
import org.progmatic.messenger.service.SessionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;


@Controller

public class MessageController implements Comparator<Message> {

    @PersistenceContext
    EntityManager em;

    MessageService ms;          //konstruktorban van
    private SessionBean ss;

    @Autowired
    public MessageController(MessageService ms, SessionBean ss) {
        this.ms = ms;
        this.ss = ss;
    }


    //elso form hivas
    @RequestMapping(path = "/message/form", method = RequestMethod.GET)
    //form letrehozaa tortenik itt az url re kattintas utan

    public String createForm(@ModelAttribute("msg1") Message message ) {             //a formot tartalmazo html feltetelezi hogy van a modelben mar egy msg1
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        message.setFrom(user.getUsername());
        return "Form";
    }

    @RequestMapping(path = "/message/form", method = RequestMethod.POST)
    public String createForm2(@ModelAttribute("msg1") Message message ) {             //a formot tartalmazo html feltetelezi hogy van a modelben mar egy msg1
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();

            message.setFrom(currentPrincipalName);
        return "Form";
    }


    //az uzenet beadasa oldalon vagyok meg fizikalisan, de a form elkuldesevel ugrok ehhez a metodushoz
@Transactional
    @RequestMapping(path = "/message/kilistaz", method = RequestMethod.POST)
    //ha valaki kuld egy post requestet az elobbi url re akkor az alatta levo metodus hivodik meg
    public String listMessage(@Valid @ModelAttribute("msg1") Message feltoltottMsg, BindingResult br, Model model) {       //@valid hivja meg az ellenorzest, ha hiba van le sem fut a metodus      a feltoltottMsg mar tartalmazza a formbol jovo adatokat is, ezt csak fel kell dolgozni
        //
        if (br.hasErrors()) {
            return "Form";

        } else {        //binding result iffel
            ss.setSender(feltoltottMsg.getFrom());//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////6

          //  System.out.println("session scope " + ss.getSender() + " - az osszes kuldo " + ss.getCounter() + " az elso kuldo hanyszor kuldott meg - " + ss.getFirstSendersNum());




           /////itt teszi  be a db be


            createMessageDB(feltoltottMsg);
            ms.sendArray(feltoltottMsg);
            //model.addAttribute("firstSender",ss.getFirstSender());
            model.addAttribute("firstSender", "family guy");


            model.addAttribute("messagearray", ms.getArray());
            model.addAttribute("majom", new SearchEntity("", "", "", ""));

            return "MessageSearcherandList";//-masik vegpontra kell redirectelni
        }
    }
    //itt kell betenni a db be is


   @Transactional
    public void createMessageDB(Message ms){
       System.out.println(ms);
        em.persist(ms);

    }

    //a kepen mar a messagesearcher latszik

    @RequestMapping(path = "/message/search", method = RequestMethod.POST)
    public String searchMessage(@ModelAttribute("majom") SearchEntity feltoltottSearch, Model model) {
        System.out.println("searchmessage");



        //a search miatt itt kiirja a user adatait
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();





        System.out.println(user.toString());





        ArrayList<Message> solutionArray = new ArrayList<>();
        solutionArray = ms.filtArray(feltoltottSearch);

        solutionArray = ms.orderArray(solutionArray, feltoltottSearch.getOrder(), feltoltottSearch.getOrdBy());


        model.addAttribute("messagearray", solutionArray);
        return "MessageSearcherandList";
    }





    @Override
    public int compare(Message o1, Message o2) {
        return 0;
    }

    @Override
    public Comparator reversed() {
        return null;
    }

    @Override
    public Comparator thenComparing(Comparator other) {
        return null;
    }

    @Override
    public Comparator thenComparingInt(ToIntFunction keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparingLong(ToLongFunction keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparingDouble(ToDoubleFunction keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparing(Function keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparing(Function keyExtractor, Comparator keyComparator) {
        return null;
    }
// http://localhost:8080/parmessage?limit=10&orderby=text&direction=desc    ezt kell meghivni
}
