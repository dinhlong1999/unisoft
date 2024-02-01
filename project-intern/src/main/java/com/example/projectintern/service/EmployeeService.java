package com.example.projectintern.service;

import com.example.projectintern.dto.employee.IEmployeeDTO;
import com.example.projectintern.model.Account;
import com.example.projectintern.model.Employee;
import com.example.projectintern.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElseThrow(null);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeByAccountUsername(String username) {
        return employeeRepository.getEmployeeByAccount_Username(username);
    }

    @Override
    public List<IEmployeeDTO> getAll(String username, String employeeName, String employeePhoneNumber) {
        return employeeRepository.getAll("%" + username + "%","%" + "%" + employeeName + "%" + "%",
                employeePhoneNumber + "%");
    }
}
