package org.progmatic.messenger.service;

import org.progmatic.messenger.model.Message;

import org.progmatic.messenger.model.SearchEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
//scope


public class MessageService {













    ArrayList<Message> messagearray = new ArrayList<>();

    public void sendArray(Message se) {
        messagearray.add(se);
    }

    public ArrayList<Message> getArray() {
        return messagearray;
    }

    public ArrayList<Message> filtArray(SearchEntity searchEntity) {
        messagearray.addAll(getArray().stream().
                filter(x -> x.getText().contains(searchEntity.getSearchText())).
                filter(y -> y.getFrom().contains(searchEntity.getSearchFrom())).
                filter(z -> z.getDate().contains(searchEntity.getSearchDate())).
                collect(Collectors.toList()));

        return messagearray;
    }

    public ArrayList<Message> orderArray(ArrayList<Message> arrayList, String order, String orderBy) {


        switch (order) {


            case ("ascent"):
                Collections.sort(arrayList, new Comparator<Message>() {
                    @Override
                    public int compare(Message m1, Message m2) {
                        switch (orderBy) {
                            case ("text"):
                                return m1.getText().compareTo(m2.getText());
                            case ("from"):
                                return m1.getFrom().compareTo(m2.getFrom());
                            case ("date"):
                                return m1.getFrom().compareTo(m2.getDate());
                        }
                        return m1.getText().compareTo(m2.getText());
                    }
                });
            case ("descent"):
                Collections.sort(arrayList, new Comparator<Message>() {
                    @Override
                    public int compare(Message m1, Message m2) {
                        switch (orderBy) {
                            case ("text"):
                                return m1.getText().compareTo(m2.getText());
                            case ("from"):
                                return m1.getFrom().compareTo(m2.getFrom());
                            case ("date"):
                                return m1.getFrom().compareTo(m2.getDate());
                        }
                        return m1.getText().compareTo(m2.getText());
                    }
                });

        }

        return arrayList;
    }



}
