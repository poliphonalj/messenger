package org.progmatic.messenger.model;

public class Message {

    public String from;
    public String date;
    public String text;


    public Message(String from, String date, String text) {//szoveg, ki , mikor
        this.from = from;
        this.date = date;
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
}
