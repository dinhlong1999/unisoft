package com.example.projectintern.dto.employee;

import com.example.projectintern.dto.employee.EmployeeInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "employeeInfo","confirmPassword"
})
@XmlRootElement(name = "updateEmployeeRequest")
public class UpdateEmployeeRequest  {
    @XmlElement(required = true)
    @NotNull(message = "Employee cannot be nut")
    @Valid
    private EmployeeInfo employeeInfo;

    @XmlElement(required = true)
    private String confirmPassword;
    public UpdateEmployeeRequest() {
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
