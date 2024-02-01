package com.example.projectintern.dto.account;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder = {
        "username","password"
})
@XmlRootElement(name = "loginRequest")
public class LoginRequest {
    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
