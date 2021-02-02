package org.progmatic.messenger.service;


import com.mysql.cj.util.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.progmatic.messenger.model.Message;

import org.progmatic.messenger.model.QMessage;
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
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;


@Service
public class MessageService {

    @PersistenceContext
    EntityManager em;

    ArrayList<Message> messagearray = new ArrayList<>();


    public void sendArray(Message se) {
        messagearray.add(se);
    }

    public ArrayList<Message> getArray() {
        return messagearray;
    }

    public ArrayList<Message> filtArray(String from, String text, String date) {
        BooleanBuilder whereCondition = new BooleanBuilder();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        if (!(StringUtils.isEmptyOrWhitespaceOnly(from))) {
            whereCondition.and(QMessage.message.from.like("%" + from + "%"));
        }
        if (!(StringUtils.isEmptyOrWhitespaceOnly(text))) {
            whereCondition.and(QMessage.message.text.like("%" + text + "%"));
        }
        //if (!(StringUtils.isEmptyOrWhitespaceOnly(ID + ""))) {
        // whereCondition.and(QMessage.message.ID.eq(Long.valueOf(ID + "")));
        //}
        if (!(StringUtils.isEmptyOrWhitespaceOnly(date))) {
            whereCondition.and(QMessage.message.date.like(date));
        }
        List<Message> resultList = (List<Message>) queryFactory.selectFrom(QMessage.message).where(whereCondition).fetch();//ez maga a lekerdezes
        messagearray.clear();
        messagearray.addAll(resultList);        //arrayliste valtoztatom
        System.out.println("mit keresek:  " + from + text + date);
        return messagearray;//ez megy vissza a kereses eredmenyekent
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
