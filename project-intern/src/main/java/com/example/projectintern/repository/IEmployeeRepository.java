package com.example.projectintern.repository;

import com.example.projectintern.dto.ICustomerDetailDTO;
import com.example.projectintern.dto.employee.IEmployeeDTO;
import com.example.projectintern.model.Account;
import com.example.projectintern.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {

    Employee getEmployeeByAccount_Username(String name);
    @Query(value = "select a.username as usernameEmployee, e.`name` as employeeName, e.phone_number as employeePhoneNumber from employee e join `account` a on  a.id = e.account_id\n" +
            "where a.username like :username and e.`name` like :employeeName and e.phone_number like :phoneNumber",nativeQuery = true)
    List<IEmployeeDTO> getAll(@Param("username") String username,
                              @Param("employeeName") String employeeName,
                              @Param("phoneNumber") String phoneNumber);

}
