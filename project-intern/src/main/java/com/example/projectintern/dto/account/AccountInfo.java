package com.example.projectintern.dto.account;

import com.example.projectintern.dto.RoleInfo;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountInfo",propOrder = {
        "id","username","password","role","flag"
})
public class AccountInfo {
    private int id;
    @XmlElement(required = true)
    @NotNull(message = "Không được để trống")
    private String username;
    @XmlElement(required = true)
    @NotNull(message = "Không được để trống")
    private String password;
    @XmlElement(required = true)
    @NotNull(message = "Không được để trống")
    private RoleInfo role;
    @XmlElement(required = true)
    private boolean flag;

    public AccountInfo() {
    }

    public AccountInfo(int id, String username, String password, RoleInfo role, boolean flag) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public RoleInfo getRole() {
        if (role == null){
            role = new RoleInfo();
        }
        return role;
    }

    public void setRole(RoleInfo role) {
        this.role = role;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
