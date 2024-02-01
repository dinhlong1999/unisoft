package com.example.projectintern.dto;

import com.example.projectintern.dto.employee.EmployeeInfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "employeeInfo"
})
@XmlRootElement(name = "getEmployeeByIdResponse")
public class GetEmployeeByIdResponse {
    private EmployeeInfo employeeInfo;

    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }
}
