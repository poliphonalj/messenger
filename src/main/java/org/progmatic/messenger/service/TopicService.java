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
       return em.createQuery("SELECT t FROM Topic t").getResultList();
    }

    public Topic findTopicById(int i){
       return em.find(Topic.class,i);
    }
}
