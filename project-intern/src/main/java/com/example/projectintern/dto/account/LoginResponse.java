package com.example.projectintern.dto.account;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="",propOrder = {
        "jwt"
})
@XmlRootElement(name = "loginResponse")
public class LoginResponse {
    @XmlElement(required = true)
    private String jwt;

    public LoginResponse() {
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
