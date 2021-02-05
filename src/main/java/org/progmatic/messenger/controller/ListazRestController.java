package org.progmatic.messenger.controller;

import org.apache.coyote.Request;
import org.progmatic.messenger.DTO.MessageDTO;
import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/rest")
public class ListazRestController {
    MessageService ms;

    @Autowired
    public ListazRestController(MessageService ms) {
        this.ms = ms;
    }

    @RequestMapping(value = "/listmessages", method = RequestMethod.GET)
    public List<MessageDTO> listMessages(
            @RequestParam(name = "from", required = false) String from,
            @RequestParam(name="text", required = false) String text,
            @RequestParam(name = "date", required = false) String date) {


        List<Message> allMessages = ms.filtArray(from, text, date);//dozerrel lehet atrakni az eredmeny lista elemeit DTO ra
        List<MessageDTO> allMessagesDTO = new ArrayList<>();
        for (int i = 0; i < allMessages.size(); i++) {
            MessageDTO msgDTO = new MessageDTO(allMessages.get(i).getFrom(), allMessages.get(i).getText(), allMessages.get(i).getDate());
            //at kell alakitani a messageket messagedto ra
            allMessagesDTO.add(msgDTO);
        }

        System.out.println(allMessages);
        return allMessagesDTO;//dto kat akarnek visszaadni hogy az ignore json annotaciokat levehessem.(vagy dto vagy @ignoreJson)
    }


    @RequestMapping(value = "/csrf", method = RequestMethod.GET)
    public CsrfToken getCsrf(CsrfToken csrf) {
        System.out.println(csrf);
        return csrf;
    }


}



