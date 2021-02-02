package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller //
public class HomeController {       //a container egy singleton beant csinal ebbol, ehhez routola a http requestet

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    //ha a weberverbe erkezik egy getrequest es a /home v / url re jon, akkor erre a metodusra iranyitsa a requestet, es erre bizza a response eloallitasat

    public String startPage(Model model) {
        model.addAttribute("message", "hello");//message neven egy hello tartalmu uzenet
        return "greeting";          //megegyezik a visszaadando html fajl nevevel -html
    }


    @RequestMapping(value = "/home2", method = RequestMethod.GET)
//ha a weberverbe erkezik egy getrequest es a /home v / url re jon, akkor erre a metodusra iranyitsa a requestet, es erre bizza a response eloallitasat
    public String startPage2(Model model) {
        model.addAttribute("message", "hello");
        return "greeting";//megegyezik a html fajl nevevel -.html ezt kuldi vissza repsonsekent az adott urlre
    }
/*
    @RequestMapping(value =  "/dobokocka", method = RequestMethod.GET )//ha a weberverbe erkezik egy getrequest es a /home v / url re jon, akkor erre a metodusra iranyitsa a requestet, es erre bizza a response eloallitasat
    public String dobokockadobas(Model model){
        Random r=new Random();
        int i=r.nextInt(6)+1;
        model.addAttribute("dobas", i);
        return "dobokocka";//megegyezik a html fajl nevevel -.html ezt kuldi vissza repsonsekent az adott urlre
    }

 */

    @RequestMapping(value = "/dobokocka2", method = RequestMethod.GET)
    public String showUserData(
            @RequestParam("sides") int sides, Model model) {
        Random r = new Random();
        int i = r.nextInt(sides) + 1;
        model.addAttribute("dobas", i);     //ennyi oldalu dobokockaval dolgozik
        return "dobokocka";

        //localhost:8080=dobokocka2?sides=  xxx         ezt kell meghivni
    }

    @PersistenceContext
    EntityManager em;
    @Transactional
    @RequestMapping(value = "/modifyMessage", method = RequestMethod.GET)
    public String modifyMessage(
            @RequestParam("id") long id, @RequestParam("text") String text, @RequestParam("sleep") int sleep, Model model) throws InterruptedException {
        //em.createQuery("update Message m set m.text where m.ID=12").setParameter(); //a setparameter miatt nem lesz mar  sql injection
        ArrayList<Message> messages=new ArrayList<>();
        Message m = em.find(Message.class, id);
        m.setText(text);
        messages.add(m);
        Thread.sleep(sleep);
        model.addAttribute("messagearray", messages);
        return "dobokocka";

        //localhost:8080=dobokocka2?sides=  xxx         ezt kell meghivni
    }


}
