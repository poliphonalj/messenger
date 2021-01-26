package org.progmatic.messenger.service;

import org.progmatic.messenger.model.Topic;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TopicService {//kiszedi a topiklistat  a dbbol es aztan ezt be kell autowirelezni a messagesender24 hez

    @PersistenceContext
    EntityManager em;

    public List<Topic> getTopicList(){
       // System.out.println("topicok listaja    "+ em.createQuery("SELECT t.name FROM Topic t").getResultList());
       return em.createQuery("SELECT t.name FROM Topic t").getResultList();
    }



}
