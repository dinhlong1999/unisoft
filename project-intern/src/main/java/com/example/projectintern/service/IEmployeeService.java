package com.example.projectintern.service;

import com.example.projectintern.dto.employee.IEmployeeDTO;
import com.example.projectintern.model.Account;
import com.example.projectintern.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployee();

    Employee getEmployeeById(int id);
    void updateEmployee(Employee employee);
    Employee getEmployeeByAccountUsername(String username);
    List<IEmployeeDTO> getAll(String username, String employeeName, String employeePhoneNumber);
};
