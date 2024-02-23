package com.example.projectintern.repository;

import com.example.projectintern.dto.ICustomerDetailDTO;
import com.example.projectintern.dto.employee.IEmployeeDTO;
import com.example.projectintern.model.Account;
import com.example.projectintern.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.transaction.Transactional;
import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee getEmployeeByAccount_Username(String name);

    @Query(value = "SELECT " +
            "           a.username      AS usernameEmployee, " +
            "           e.`name`        AS employeeName, " +
            "           e.phone_number  AS employeePhoneNumber " +
            "       FROM " +
            "           employee e " +
            "       JOIN " +
            "           `account` a ON  a.id = e.account_id\n" +
            "       WHERE " +
            "            a.username LIKE :username " +
            "       AND " +
            "            e.`name` LIKE :employeeName " +
            "       AND " +
            "            e.phone_number LIKE :phoneNumber", nativeQuery = true)
    List<IEmployeeDTO> getAll(@Param("username") String username,
                              @Param("employeeName") String employeeName,
                              @Param("phoneNumber") String phoneNumber);

    @Modifying
    @Transactional
    @Query(value = "UPDATE " +
            "           employee " +
            "       SET " +
            "           `name`        = :name," +
            "            phone_number = :phoneNumber," +
            "            account_id   = :account," +
            "            version      = :version + 1 " +
            "       WHERE " +
            "            id = :id " +
            "       AND " +
            "            version = :versionUpdate", nativeQuery = true)
    int updateEmployee(@Param("name") String employeeName,
                       @Param("phoneNumber") String phoneNumber,
                       @Param("account") int accountId,
                       @Param("version") int version,
                       @Param("id") int employeeId,
                       @Param("versionUpdate") int versionUpdate);

    @Modifying
    @Query(value = "INSERT INTO employee(" +
            "            `name`," +
            "             phone_number," +
            "             account_id) " +
            "       VALUES(" +
            "             :name," +
            "             :phone_number," +
            "             :accountId" +
            "             )",nativeQuery = true)
    void saveEmployee(@Param("name") String name,
                      @Param("phone_number")String phoneNumber,
                      @Param("accountId") int accountId);


}
