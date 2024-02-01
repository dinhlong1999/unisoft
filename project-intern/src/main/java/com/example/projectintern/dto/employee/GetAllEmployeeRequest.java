package com.example.projectintern.dto.employee;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "username","employeeName","employeePhoneNumber"
})
@XmlRootElement(name = "getAllEmployeeRequest")
public class GetAllEmployeeRequest {
    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private String employeeName;
    @XmlElement(required = true)
    private String employeePhoneNumber;

    public GetAllEmployeeRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }
}
