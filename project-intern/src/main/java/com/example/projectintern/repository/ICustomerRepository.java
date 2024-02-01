package com.example.projectintern.repository;

import com.example.projectintern.dto.ICustomerDetailDTO;
import com.example.projectintern.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value = "select c.id,c.address,c.flag,c.`name`,c.phone_number as phoneNumber, c.version, e.`name` as nameEmployee,e.id as idEmployee,e.phone_number as phoneNumberEmployee from customer c\n" +
            "join employee e on e.id = c.employee_id\n" +
            "where c.`name` like :nameCustomer and c.phone_number like :phoneNumberCustomer",nativeQuery = true)
    Page<ICustomerDetailDTO> findCustomerByNameAndPhoneNumber(Pageable pageable,
                                                              @Param("nameCustomer") String nameCustomer,
                                                              @Param("phoneNumberCustomer") String phoneNumberCustomer);

    @Query(value = "select count(*) from customer where `name` like :nameCustomer and phone_number like :phoneNumber order by `name`;", nativeQuery = true)
    int totalRowCustomer (@Param("nameCustomer") String nameCustomer,
                  @Param("phoneNumber") String phoneNumber);

    @Query(value = "select c.id,c.address,c.flag,c.`name`,c.phone_number as phoneNumber, c.version, e.`name` as nameEmployee,e.id as idEmployee,e.phone_number as phoneNumberEmployee from customer c\n" +
            "join employee e on e.id = c.employee_id\n" +
            "where c.`name` like :nameCustomer and c.phone_number like :phoneNumberCustomer limit :limitNumber offset :page",nativeQuery = true)
    List<ICustomerDetailDTO> getCustomerByNameAndPhoneNumber(@Param("limitNumber") int limitNumber,
                                                              @Param("nameCustomer") String nameCustomer,
                                                              @Param("phoneNumberCustomer") String phoneNumberCustomer,
                                                             @Param("page") int page);

    Customer findCustomerByPhoneNumber(String phoneNumber);
}
