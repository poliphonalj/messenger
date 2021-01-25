package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.MyUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserListController {

    @RequestMapping(path = "/message/userList", method = RequestMethod.GET)
    public String userList() {

        return "userList";
    }



}
