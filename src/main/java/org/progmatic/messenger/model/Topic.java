package org.progmatic.messenger.model;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;


@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int ID;
    @Column(name="topicName")
    String name;
    String description;
    @OneToMany(mappedBy="topic")
    private List<Message> messagesList;


    public Topic(int ID, String name, String description) {
        this.ID=ID;
        this.name = name;
        this.description = description;
    }

    public Topic() {
    }




    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
