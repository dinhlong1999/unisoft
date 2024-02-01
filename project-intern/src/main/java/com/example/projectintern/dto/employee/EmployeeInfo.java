package com.example.projectintern.dto.employee;

import com.example.projectintern.dto.account.AccountInfo;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeeInfo",propOrder = {
        "id","name","phoneNumber","flag","account","version"
})
public class EmployeeInfo {
    private int id;
    @XmlElement(required = true)
    @NotNull(message = "Không được để trống")
    private String name;
    @XmlElement(required = true)
    @NotNull(message = "Không được để trống")
    private String phoneNumber;
    @XmlElement(required = true)
    private boolean flag;
    @XmlElement(required = true)
    @NotNull(message = "Không được để trống")
    private AccountInfo account;
    @XmlElement(required = true)
    private int version;

    public EmployeeInfo() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public AccountInfo getAccount() {
        if (account == null){
            account = new AccountInfo();
        }
        return account;
    }

    public void setAccount(AccountInfo accountInfo) {
        this.account = accountInfo;
    }
}
