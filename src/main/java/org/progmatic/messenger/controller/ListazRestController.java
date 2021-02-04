package org.progmatic.messenger.controller;

import org.apache.coyote.Request;
import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;




    @RestController
    @RequestMapping("/rest")
    public class ListazRestController {
        MessageService ms;

        @Autowired
        public ListazRestController(MessageService ms) {
            this.ms = ms;
        }

        @RequestMapping( value="/listmessages", method = RequestMethod.GET)
        public List<Message> listMessages(@RequestParam ("from") String from, @RequestParam ("text") String text, @RequestParam ("date") String date) {
            List<Message> allMessages = ms.filtArray(from,text,date);
            System.out.println(allMessages);
            return allMessages;
        }
    }



