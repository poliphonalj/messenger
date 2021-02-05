package org.progmatic.messenger.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//csak olyan fieldeket veszek fel amit latni akarok a jasonban majd
public class MessageDTO {
String from;
String text;
String date;

    public MessageDTO() {
    }

    public MessageDTO(String text) {
        this.text = text;
    }

    public MessageDTO(String from, String text, String date) {
        this.from = from;
        this.text = text;
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
