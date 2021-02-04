package org.progmatic.messenger.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
public class Message {
    @NotNull @Size(min=2,max=25)
    @Column(name="sender")
    public String from;

   // ArrayList<Message>comments;

    int commented;

   @Version
    long dbseged;


    @NotNull @Size(min=1,max=250)
    public String text;
    public String date;

    public long getDbseged() {
        return dbseged;
    }

    public void setDbseged(long dbseged) {
        this.dbseged = dbseged;
    }

    @Id
    @GeneratedValue
    public long ID;
    @ManyToOne
    @JsonIgnore
    private Topic topic;//toppic mapby -a
     int sizeOfComments;

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    @ManyToOne
    @JsonIgnore
    private MyUser user;



    public Message(String from,String text,long ID) {//szoveg, ki , mikor
        this.from = from;
       // this.date = java.time.LocalDateTime.now().toString();
        this.text = text;
        this.ID=ID;
  //      comments=null;
        commented=0;

    }

    public Message(@NotNull @Size(min = 2, max = 25) String from, @NotNull @Size(min = 1, max = 250) String text, String date) {
        this.from = from;
        this.text = text;
        this.date = date;
    }

    public Message() {
    }

  //  public ArrayList<Message> getComments() {
 //       return comments;
   // }

  //  public void setComments(ArrayList<Message> comments) {
    //    this.comments = comments;
    //}

    public int getCommented() {
        return commented;
    }

    public void setCommented(int commented) {
        this.commented = commented;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

  //  public int getSizeOfComments(){
   //      sizeOfComments=comments.size() ;
     //   return comments.size();
   // }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
