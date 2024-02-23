package com.example.projectintern.dto.employee;

import com.example.projectintern.dto.employee.EmployeeInfo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "employeeInfo","confirmPassword"
})
@XmlRootElement(name = "saveEmployeeRequest")
public class SaveEmployeeRequest {

    @XmlElement(required = true)
    private EmployeeInfo employeeInfo;
    @XmlElement(required = true)
    private String confirmPassword;

    public SaveEmployeeRequest() {
    }

    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
