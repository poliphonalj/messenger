package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.Message;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class MessageSenderController {


    @RequestMapping(path = "/message/addMessage", method = RequestMethod.GET)
    public String createForm(@ModelAttribute("msg1") Message message ) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        message.setFrom(user.getUsername());
        return "addMessage";
    }

    @RequestMapping(path = "/message/addMessage", method = RequestMethod.POST)
    public String createForm2(@ModelAttribute("msg1") Message message ) {             //a formot tartalmazo html feltetelezi hogy van a modelben mar egy msg1
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        message.setFrom(currentPrincipalName);
        return "addMessage";
    }
}
