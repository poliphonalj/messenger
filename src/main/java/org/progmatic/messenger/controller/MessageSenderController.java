package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.model.Topic;
import org.progmatic.messenger.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
public class MessageSenderController {

@Autowired
TopicService ts;

    @RequestMapping(path = "/message/addMessage", method = RequestMethod.GET)
    public String createForm(@ModelAttribute("msg1") Message message , Model model){
        List<Topic> topics= ts.getTopicList();

        model.addAttribute("topicarray",topics);
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        message.setFrom(user.getUsername());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("loggedName", currentPrincipalName);
        return "addMessage";
    }
    @Transactional
    @RequestMapping(path = "/message/addMessage", method = RequestMethod.POST)
    public String createForm2(@ModelAttribute("msg1") Message message ) {             //a formot tartalmazo html feltetelezi hogy van a modelben mar egy msg1
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        message.setFrom(currentPrincipalName);

        System.out.println("bejelentkezve"+currentPrincipalName);
        return "addMessage";
    }
}
