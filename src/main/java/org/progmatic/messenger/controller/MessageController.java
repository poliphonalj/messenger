package org.progmatic.messenger.controller;

import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.model.SearchEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

@Controller
public class MessageController implements Comparator<Message> {
    ArrayList<Message> messagearray = new ArrayList<>();
    ArrayList<Message>solutionArray=new ArrayList<>();



    @RequestMapping(path = "/message/form", method = RequestMethod.GET)         //form letrehozaa tortenik itt az url re kattintas utan
    public String createForm(@ModelAttribute("msg1") Message message){             //a modelbe rakok egy Message objektumot msg1 neven
        return "Form";                                                             //meghivom a Form lapoot
    }



    //az uzenet beadasa oldalon vagyok meg fizikalisan, de a form elkuldesevel ugrok ehhez a metodushoz

    @RequestMapping(path = "/message/create", method = RequestMethod.POST)                      //ha valaki kuld egy post requestet az elobbi url re akkor az alatta levo metodus hivodik meg
    public String createMessage(@ModelAttribute("msg1") Message feltoltottMsg, Model model){       //a feltoltottMsg mar tartalmazza a formbol jovo adatokat is, ezt csak fel kell dolgozni
        feltoltottMsg.setDate(java.time.LocalDateTime.now().toString());
        messagearray.add(feltoltottMsg);
        model.addAttribute("messagearray", messagearray);
        model.addAttribute("majom",new SearchEntity("","","",""));
        return "MessageSearcherandList";                                                                 //-masik vegpontra kell redirectelni
    }






    //a kepen mar a messagesearcher latszik

    @RequestMapping(path = "/message/search", method = RequestMethod.POST)
    public String searchMessage(@ModelAttribute("majom") SearchEntity feltoltottSearch, Model model) {

        solutionArray.addAll(messagearray.stream().
                        filter(x->x.getText().contains(feltoltottSearch.getSearchText())).
                        filter(y->y.getFrom().contains(feltoltottSearch.getSearchFrom())).
                        filter(z->z.getDate().contains(feltoltottSearch.getSearchDate())).
                        filter(s->s.getDate().contains(feltoltottSearch.getSearchDate())).
                        collect(Collectors.toList()));
        System.out.println(solutionArray.get(0).getFrom().toString());
        //return "redirect:/message/solutionList";
        model.addAttribute("messagearray", solutionArray);
        return "MessageSearcherandList";
    }







    @Override
    public int compare(Message o1, Message o2) {
        return 0;
    }

    @Override
    public Comparator reversed() {
        return null;
    }

    @Override
    public Comparator thenComparing(Comparator other) {
        return null;
    }

    @Override
    public Comparator thenComparingInt(ToIntFunction keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparingLong(ToLongFunction keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparingDouble(ToDoubleFunction keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparing(Function keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparing(Function keyExtractor, Comparator keyComparator) {
        return null;
    }
// http://localhost:8080/parmessage?limit=10&orderby=text&direction=desc    ezt kell meghivni






}
  /* @RequestMapping(value = "/message", method = RequestMethod.GET)       //ezt kell meghivni a bongeszobol

    public String getMessage(Model model) {
        Message m1 = new Message("sanyi", "14:00", "szia hogy vagy?");
        Message m2 = new Message("béla", "19:00", "koszi jol");
        messagearray.add(m1);
        messagearray.add(m2);

        model.addAttribute("messagearray", messagearray);    //ilyen neven ezt teszem bele
        return "MessageSender";          //ezt a htmlt adja vissza
    }



    @RequestMapping(value = "/parmessage", method = RequestMethod.GET)
    public String getParameterisedMessage(
            @RequestParam(name= "limit" ,required = false) int num,
            @RequestParam(name="orderby") String order,
            @RequestParam(name= "direction") String dir,
            Model model) {





        //limitalas




        if (num > messagearray.size()) {
            num = messagearray.size();
        }
        messagearray.subList(num, messagearray.size()).clear();


        //order by
        switch (order) {
            case "from":
                Collections.sort(messagearray, (msg1, msg2) -> msg1.getFrom().compareTo(msg2.getFrom()));
                break;

            case "date":
                Collections.sort(messagearray, (msg3, msg4) -> msg3.getDate().compareTo(msg4.getDate())
                );
                break;

            case "text":
                Collections.sort(messagearray, (msg5, msg6) -> msg5.getText().compareTo(msg6.getText())
                );
                break;
        }

        //csokkeno v novekvobe rendezes

        switch(dir){
            case "asc":
                break;
            case "desc":
                Collections.reverse(messagearray);
                break;
        }


        model.addAttribute("messagearray", messagearray);     //messagearray neven adom at az arrayt
        return "MessageSender";                                    //ezt a html t adja vissza
    }






    @RequestMapping(value = "/uzenet/{uzenetId}", method = RequestMethod.GET)
    public String getNumberedMessage(@PathVariable(name = "uzenetId")int uzenetId, Model model) {   //a modelt a spring adja nekem


        model.addAttribute("messagearray", messagearray.get(uzenetId-1));
        model.addAttribute("uzenetId", uzenetId-1);
        return "MessageSender";
    }


*/