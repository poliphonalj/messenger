package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.model.SearchEntity;
import org.progmatic.messenger.model.Topic;
import org.progmatic.messenger.service.MessageService;
import org.progmatic.messenger.service.SessionBean;
import org.progmatic.messenger.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;


@Controller
public class SearchAndListController implements Comparator<Message> {

    @PersistenceContext
    EntityManager em;

    Topic t;
    Topic u;
    Topic s;
    Topic z;
    MessageService ms;
    private SessionBean ss;



    @Autowired
    public SearchAndListController(MessageService ms, SessionBean ss) {
        this.ms = ms;
        this.ss = ss;
        t=new Topic();
        u=new Topic();
        s=new Topic();
        z=new Topic();
    }



    //az uzenet beadasa oldalon vagyok meg fizikalisan, de a form elkuldesevel ugrok ehhez a metodushoz
    @Transactional
    @RequestMapping(path = "/message/kilistaz", method = RequestMethod.POST)

    public String listMessage(@Valid @ModelAttribute("msg1") Message feltoltottMsg, BindingResult br, Model model) {       //@valid hivja meg az ellenorzest, ha hiba van le sem fut a metodus      a feltoltottMsg mar tartalmazza a formbol jovo adatokat is, ezt csak fel kell dolgozni
        if (br.hasErrors()) {
            return "addMessage";
        } else {
            ss.setSender(feltoltottMsg.getFrom());

//itt teszi  be a db be

            feltoltottMsg.setDate(LocalDateTime.now().toString());

            createMessageDB(feltoltottMsg);
            System.out.println("ceatetopic mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
            createTopic();//honnan kap Topicot



            /////////////////////////////////////////////////////////////////////////////////////////////
            List<Message>resultList;

            resultList= findAllDB();








            ms.sendArray(feltoltottMsg);
            //model.addAttribute("firstSender",ss.getFirstSender());
           // model.addAttribute("firstSender", "family guy");
           // model.addAttribute("messagearray", ms.getArray());//////////////////////////
            model.addAttribute("messagearray", resultList);


            model.addAttribute("majom", new SearchEntity("", "", "", ""));
            return "MessageSearcherandList";//-masik vegpontra kell redirectelni
        }
    }

    @Transactional
    public void createMessageDB(Message ms){
       System.out.println(ms);
        em.persist(ms);
    }

    @Transactional
    public void createTopic(){
        System.out.println("create topic:lllllllllllllllllllllllllllllllllllllll"+t.toString());
        em.persist(t);
        t.setName("sport");
        em.persist(u);
        u.setName("idojaras");
        em.persist(s);
        s.setName("autok");
        em.persist(z);
        z.setName("mozi");



    }


    @Transactional
    public List<Message> findAllDB(){
        return em.createQuery( "SELECT m FROM Message m").getResultList();
    }

    //a kepen mar a messagesearcher latszik



    @RequestMapping(path = "/message/search", method = RequestMethod.POST)
    public String searchMessage(@ModelAttribute("majom") SearchEntity feltoltottSearch, Model model) {
        System.out.println("searchmessage");

        //a search miatt itt kiirja a user adatait
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
}
