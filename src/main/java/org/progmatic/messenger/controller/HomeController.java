package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private HomeService hs;

    @Autowired
    public HomeController(HomeService hs){
        this.hs=hs;
    }


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

    @RequestMapping(value = "/dobokocka2", method = RequestMethod.GET)//olvasas
    public String showUserData(
            @RequestParam("sides") int sides, Model model) {
        Random r = new Random();
        int i = r.nextInt(sides) + 1;
        model.addAttribute("dobas", i);     //ennyi oldalu dobokockaval dolgozik
        return "dobokocka";

        //
    }





    @RequestMapping(value = "/appendTextToMsg", method = RequestMethod.GET)//iras
    public String modifyMessage(
            @RequestParam("id") long id, @RequestParam("text") String text,@RequestParam("sleep")int sleep) throws InterruptedException {
        //em.createQuery("update Message m set m.text where m.ID=12").setParameter(); //a setparameter miatt nem lesz mar  sql injection
        hs.dbAppend(id,"elobb",10);
        hs.dbAppend2(id,"sheep",10000);
       // model.addAttribute("messagearray", hs.dbAppend(id,text,sleep));// itt vn a lekerdezes
        //System.out.println("dffdf"+hs.getmList());
        return "dobokocka";
    }


    @RequestMapping(value = "/showMessage", method = RequestMethod.GET)
    public String showMessage(
            @RequestParam("id") long id,  @RequestParam("sleep") int sleep, Model model) throws InterruptedException {
        //em.createQuery("update Message m set m.text where m.ID=12").setParameter(); //a setparameter miatt nem lesz mar  sql injection
        hs.setId(id);
        model.addAttribute("messagearray", hs.showMessage(id,sleep));// itt vn a lekerdezes
        return "dobokocka";
    }




}
