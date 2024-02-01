package com.example.projectintern.dto.customer;

import com.example.projectintern.dto.employee.EmployeeInfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerInfo", propOrder = {
        "id","name","phoneNumber","address","flag","version","employee"
})
public class CustomerInfo {
    private int id;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String phoneNumber;
    @XmlElement(required = true)
    private String address;
    @XmlElement(required = true)
    private boolean flag;
    @XmlElement(required = true)
    private int version;

    @XmlElement(required = true)
    private EmployeeInfo employee;

    public CustomerInfo() {
    }

    public CustomerInfo(int id,
                        String name,
                        String phoneNumber,
                        String address,
                        boolean flag,
                        int version,
                        EmployeeInfo employee ) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.flag = flag;
        this.version = version;
        this.employee = employee;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public EmployeeInfo getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeInfo employee) {
        this.employee = employee;
    }
}
