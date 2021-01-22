package org.progmatic.messenger.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope( scopeName = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)

public class SessionBean {
    String sender;
    String firstSender;
    int i=0;
    int j=0;
    public SessionBean(){

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        i++;
        if(i==1){
            this.sender = sender;
            firstSender=sender;
        }
        if(sender.equals(firstSender)){
            j++;
        }

    }

    public int getCounter(){
       return i;
    }

    public int getFirstSendersNum(){
        return j;
    }

    public String getFirstSender() {
        return firstSender;
    }
}
