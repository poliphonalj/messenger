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
    private TopicService topicService;

    @Autowired
    public SearchAndListController(MessageService ms, SessionBean ss, TopicService topicService) {
        this.ms = ms;
        this.ss = ss;
        this.topicService=topicService;
        t = new Topic();
        u = new Topic();
        s = new Topic();
        z = new Topic();
    }


    //az uzenet beadasa oldalon vagyok meg fizikalisan, de a form elkuldesevel ugrok ehhez a metodushoz
    @Transactional
    @RequestMapping(path = "/message/kilistaz", method = RequestMethod.POST)

    public String listMessage(@Valid @ModelAttribute("msg1") Message feltoltottMsg, BindingResult br, Model model,
                              @ModelAttribute("topic.ID") int seged) { //rakja abe a modelbe egy uj msg1 neven          a feltoltottMsg mar tartalmazza a formbol jovo adatokat is, ezt csak fel kell dolgozni
        if (br.hasErrors()) {
            System.out.println("brerror");
            return "addMessage";
        } else {
            Topic topik=topicService.findTopicById(seged);
            ss.setSender(feltoltottMsg.getFrom());
            feltoltottMsg.setTopic(topik);
            topik.getMessagesList().add(feltoltottMsg);
            em.persist(feltoltottMsg);
            em.merge(topik);


//itt teszi  be a db be
            feltoltottMsg.setDate(LocalDateTime.now().toString());
            createMessageDB(feltoltottMsg);
            createTopic();//honnan kap Topicot
            List<Message> resultList;
            resultList = findAllDB();
            ms.sendArray(feltoltottMsg);
            model.addAttribute("messagearray", resultList);
            model.addAttribute("majom", new SearchEntity("", "", "", ""));
            return "MessageSearcherandList";//-masik vegpontra kell redirectelni
        }
    }

    @Transactional
    public void createMessageDB(Message ms) {
        em.persist(ms);
    }

    @Transactional
    public void createTopic() {

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
    public List<Message> findAllDB() {
        return em.createQuery("SELECT m FROM Message m").getResultList();
    }

    //a kepen mar a messagesearcher latszik


    @RequestMapping(path = "/message/search", method = RequestMethod.POST)
    public String searchMessage(@ModelAttribute("majom") SearchEntity feltoltottSearch, Model model) {

        //a search miatt itt kiirja a user adatait
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
