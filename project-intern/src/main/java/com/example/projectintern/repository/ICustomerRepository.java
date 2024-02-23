package com.example.projectintern.repository;

import com.example.projectintern.dto.ICustomerDetailDTO;
import com.example.projectintern.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value = "SELECT " +
            "            c.id," +
            "            c.address," +
            "            c.flag," +
            "            c.`name`," +
            "            c.phone_number      AS phoneNumber," +
            "            c.version, e.`name` AS nameEmployee," +
            "            e.id                AS idEmployee," +
            "            e.phone_number      AS phoneNumberEmployee " +
            "       FROM " +
            "            customer c\n" +
            "       JOIN " +
            "            employee e     ON e.id = c.employee_id\n" +
            "       WHERE " +
            "            c.`name`       LIKE :nameCustomer " +
            "       AND " +
            "            c.phone_number LIKE :phoneNumberCustomer",nativeQuery = true)
    Page<ICustomerDetailDTO> findCustomerByNameAndPhoneNumber(Pageable pageable,
                                                              @Param("nameCustomer") String nameCustomer,
                                                              @Param("phoneNumberCustomer") String phoneNumberCustomer);

    @Query(value = "SELECT " +
            "            count(*) " +
            "       FROM " +
            "            customer " +
            "       WHERE " +
            "           `name` LIKE :nameCustomer " +
            "       AND " +
            "            phone_number LIKE :phoneNumber " +
            "       ORDER BY " +
            "           `name`;", nativeQuery = true)
    int totalRowCustomer (@Param("nameCustomer") String nameCustomer,
                  @Param("phoneNumber") String phoneNumber);

    @Query(value = "SELECT " +
            "            c.id," +
            "            c.address," +
            "            c.flag," +
            "            c.`name`," +
            "            c.phone_number      AS phoneNumber, " +
            "            c.version, e.`name` AS nameEmployee," +
            "            e.id                AS idEmployee," +
            "            e.phone_number      AS phoneNumberEmployee " +
            "       FROM " +
            "            customer c\n" +
            "       JOIN " +
            "            employee e ON e.id = c.employee_id\n" +
            "       WHERE " +
            "            c.`name` LIKE :nameCustomer " +
            "       AND " +
            "           c.phone_number LIKE :phoneNumberCustomer " +
            "       LIMIT " +
            "          :limitNumber offset :page",nativeQuery = true)
    List<ICustomerDetailDTO> getCustomerByNameAndPhoneNumber(@Param("limitNumber") int limitNumber,
                                                              @Param("nameCustomer") String nameCustomer,
                                                              @Param("phoneNumberCustomer") String phoneNumberCustomer,
                                                             @Param("page") int page);

    Customer findCustomerByPhoneNumber(String phoneNumber);

    @Modifying
    @Query(value = "INSERT INTO customer(" +
            "             `address`, " +
            "             `name`," +
            "              phone_number," +
            "              employee_id)\n" +
            "       VALUES (" +
            "             :address, " +
            "             :name, " +
            "             :phone_number, " +
            "             :employeeId)",nativeQuery = true)
    int saveCustomer(@Param("address") String address,
                      @Param("name") String name,
                      @Param("phone_number") String phoneNumber,
                      @Param("employeeId") int employeeId);

    @Modifying
    @Query(value = "UPDATE " +
            "           customer " +
            "       SET " +
            "           `address`      = :address ," +
            "           `name`         = :name ," +
            "           `phone_number` = :phone_number , " +
            "            version       = :version + 1 ," +
            "            employee_id   = :employeeId  " +
            "       WHERE " +
            "            id = :customerId AND version = :version",nativeQuery = true)
    int updateCustomer(@Param("address") String address,
                       @Param("name") String name,
                       @Param("phone_number") String phoneNumber,
                       @Param("version") int version,
                       @Param("employeeId") int employeeId,
                       @Param("customerId") int customerId);


}
