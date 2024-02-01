package com.example.projectintern.dto.employee;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "employeeDTO"
})
@XmlRootElement(name = "getAllEmployeeResponse")
public class GetAllEmployeeResponse  {
    @XmlElement(required = true)
    private List<EmployeeDTO> employeeDTO;

    public GetAllEmployeeResponse() {
    }

    public List<EmployeeDTO> getEmployees() {
        if (employeeDTO == null){
            employeeDTO = new ArrayList<>();
        }
        return employeeDTO;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employeeDTO = employees;
    }
}
