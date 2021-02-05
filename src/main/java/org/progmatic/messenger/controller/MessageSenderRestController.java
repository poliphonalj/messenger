package org.progmatic.messenger.controller;


import org.progmatic.messenger.DTO.MessageDTO;
import org.progmatic.messenger.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@RestController

//@RequestMapping("/rest")
public class MessageSenderRestController {

    private static final Logger LOG = LoggerFactory.getLogger(MessageSenderRestController.class);
    @PersistenceContext
    EntityManager em;

    @Transactional
    @RequestMapping(path = "/rest/message", method = RequestMethod.POST)
    public MessageDTO createMessage3(@RequestBody MessageDTO messageDTO) {            //a rewuest bodyjaban levo json kiveszi es teszi a dto ba
        LOG.debug("createMessage3 called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        messageDTO.setFrom(currentPrincipalName);
        Message m = new Message(messageDTO.getFrom(), messageDTO.getText(), LocalDateTime.now().toString());
//ki kell kerni a topicot a db bol es azt is hozzatenni
        //itt el kell menteni es visszaterni hogy pl sikeresen tert vissza
        //betenni a db be

        em.persist(m);
        LOG.debug("mesage added to database");
        MessageDTO ret = new MessageDTO();
        ret.setText(m.getText());
        return ret;
    }


}
