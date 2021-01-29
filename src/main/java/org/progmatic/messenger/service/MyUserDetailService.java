package org.progmatic.messenger.service;

import org.progmatic.messenger.model.MyUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @PersistenceContext
    EntityManager em;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {//vigyazat sql injection le kell vedeni
        List<MyUser> userList= em.createQuery("SELECT my FROM MyUser my").getResultList();
        for (MyUser user : userList) {
            if(user.getUsername().equals(s)){
                return user;
            }
        }
        return null;
    }

    public void addUser(MyUser myUser){
        em.persist(myUser);
    }
}
