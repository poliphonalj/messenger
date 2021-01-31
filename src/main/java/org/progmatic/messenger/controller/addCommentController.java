package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.model.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class addCommentController {

    //a messagesearcherandlist rol erkezo hivas
    @RequestMapping(path = "/message/addComment", method = RequestMethod.POST)
    public String createForm2(@ModelAttribute(value = "mes") Message mes, Model model) {


        model.addAttribute("message", mes);
        return "addComment";
    }


    @RequestMapping(path = "/message/addComment2", method = RequestMethod.GET)
    public String createForm(@ModelAttribute(value = "mes") Message mes, Model model) {

        //semmi
        return "addComment";
    }


}
