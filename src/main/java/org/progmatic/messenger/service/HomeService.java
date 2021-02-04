package org.progmatic.messenger.service;

import org.progmatic.messenger.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;


@Service
public class HomeService {

    @PersistenceContext
    EntityManager em;
    long id;
    String text;
    int sleep;
    ArrayList<Message>mList=new ArrayList<>();



    public HomeService() throws InterruptedException {
    }


    //a db adott id ju uzenetehez fuz egy _hellot   IRAS
    @Transactional
        public ArrayList<Message> dbAppend(long id,String text,long sleep) throws InterruptedException {
        ArrayList<Message> mList = new ArrayList<>();
        Message m = em.find(Message.class, id, LockModeType.PESSIMISTIC_WRITE);
        m.setText(text.concat("_"+text));
        mList.add(m);

        //Thread.sleep(sleep);
        return mList;
        }//ir
        //http://localhost:8080/appendTextToMsg?id=2&text=hello&sleep=10

    @Transactional
    public ArrayList<Message> dbAppend2(long id,String text,long sleep) throws InterruptedException {
        ArrayList<Message> mList = new ArrayList<>();
        Message m = em.find(Message.class, id, LockModeType.PESSIMISTIC_WRITE);
        m.setText(text.concat("_"+text));
        mList.add(m);

        //Thread.sleep(sleep);
        return mList;
    }//ir
    //http://localhost:8080/appendTextToMsg?id=2&text=hello&sleep=10000



    //a db adott id ju uzenetet kiolvassa es megjeleniti    OLVASAS
    @Transactional
    public ArrayList<Message> showMessage(long id,  long sleep) throws InterruptedException {
        ArrayList<Message> mList = new ArrayList<>();
        Message m = em.find(Message.class, id,LockModeType.PESSIMISTIC_WRITE);
        Thread.sleep(sleep);
        mList.add(m);
        return mList;
    }//olvas
    //http://localhost:8080/showMessage?id=2&&sleep=10000


    /*
    1. ket kulonbozo bongeszobol elinditva az olvasat es kozben az irast.
        olvas:  eredetiuzenet_hello     >>>>db ben is atirva
        iras:
        nincs beallitva a tranzakcios izolacios szint

    2. ket konkat egyszerre tranzakcios izolacios szint nelkul
        olvas: eredetiuzenet_eredetiuzenet

    3.

     */





    public ArrayList<Message> getmList() {
        return mList;
    }

    public void setmList(ArrayList<Message> mList) {
        this.mList = mList;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }
}
