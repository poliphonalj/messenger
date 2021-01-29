package org.progmatic.messenger.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity
public class MyUser implements UserDetails {            //entitassa tenni
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @GeneratedValue
    @Id
    int userId;
    String userName ="";
    String birth="";
    String email="";
    String password="";
    String roles="";
    String ROLE_PREFIX = "ROLE_";

    public MyUser( String userName, String birth, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public MyUser(){}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + "ADMIN"));
        return list;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getROLE_PREFIX() {
        return ROLE_PREFIX;
    }

    public void setROLE_PREFIX(String ROLE_PREFIX) {
        this.ROLE_PREFIX = ROLE_PREFIX;
    }
}
