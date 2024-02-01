package com.example.projectintern.service;

import com.example.projectintern.dto.ICustomerDetailDTO;
import com.example.projectintern.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerService {
    Page<ICustomerDetailDTO> findCustomerByNameAndPhoneNumber(Pageable pageable,String nameCustomer, String phoneNumber);

    int totalRowCustomer(String nameCustomer, String phoneNumber);
    List<ICustomerDetailDTO> getCustomerByNameAndPhoneNumber(String nameCustomer, String phoneNumberCustomer, int limitNumber,int page);

    Customer saveCustomer(Customer customer);
    Customer findCustomerByPhoneNumber(String phoneNumber);

    Customer findCustomerById(int id);
}
