package com.example.projectintern.dto.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeeDT0",propOrder = {
        "usernameEmployee","employeeName",
        "employeePhoneNumber"
})
public class EmployeeDTO {
    @XmlElement(required = true)
    private String usernameEmployee;
    @XmlElement(required = true)
    private String employeeName;
    @XmlElement(required = true)
    private String employeePhoneNumber;

    public EmployeeDTO() {
    }

    public String getUsernameEmployee() {
        return usernameEmployee;
    }

    public void setUsernameEmployee(String usernameEmployee) {
        this.usernameEmployee = usernameEmployee;
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
