package org.progmatic.messenger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class Message {
    @NotNull @Size(min=2,max=25)
    @Column(name="sender")
    public String from;

    @NotNull @Size(min=1,max=250)
    public String text;
    public String date;
    @Id
    @GeneratedValue
    public long ID;

    public Message(Long ID,String from,String text) {//szoveg, ki , mikor
        this.from = from;
        this.date = java.time.LocalDateTime.now().toString();
        this.text = text;
        this.ID=ID;
    }

    public Message(@NotNull @Size(min = 2, max = 25) String from, @NotNull @Size(min = 1, max = 250) String text, String date) {
        this.from = from;
        this.text = text;
        this.date = date;
    }

    public Message() {
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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
