package org.progmatic.messenger.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;
public class Message {
    @NotNull @Size(min=2,max=25)
    public String from;

    @NotNull @Size(min=1,max=250)
    public String text;
    public String date;




    public Message(String from,String text) {//szoveg, ki , mikor
        this.from = from;
        this.date = java.time.LocalDateTime.now().toString();
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
