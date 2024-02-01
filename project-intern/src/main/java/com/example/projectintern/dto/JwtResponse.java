package com.example.projectintern.dto;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class JwtResponse implements Serializable {
    private String jwtToken;
    private String userName;

    private Collection<? extends GrantedAuthority> granList;

    public JwtResponse() {
    }

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public JwtResponse(String jwtToken, String userName, Collection<? extends GrantedAuthority> granList) {
        this.jwtToken = jwtToken;
        this.userName = userName;
        this.granList = granList;

    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Collection<? extends GrantedAuthority> getGranList() {
        return granList;
    }

    public void setGranList(Collection<? extends GrantedAuthority> granList) {
        this.granList = granList;
    }

}