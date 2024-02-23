package com.example.projectintern.service;

import com.example.projectintern.dto.ICustomerDetailDTO;
import com.example.projectintern.model.Customer;
import com.example.projectintern.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public Page<ICustomerDetailDTO> findCustomerByNameAndPhoneNumber(Pageable pageable, String nameCustomer, String phoneNumber) {
        return customerRepository.findCustomerByNameAndPhoneNumber(pageable,"%"+nameCustomer+"%",phoneNumber + "%");
    }

    @Override
    public int totalRowCustomer(String nameCustomer, String phoneNumber) {
        return customerRepository.totalRowCustomer("%"+nameCustomer + "%",phoneNumber+ "%");
    }

    @Override
    public List<ICustomerDetailDTO> getCustomerByNameAndPhoneNumber(String nameCustomer, String phoneNumberCustomer, int limitNumber, int page) {
        return customerRepository.getCustomerByNameAndPhoneNumber(limitNumber,"%"+nameCustomer + "%",phoneNumberCustomer+ "%",page);
    }

    @Override
    @Transactional
    public int saveCustomer(Customer customer) {
      return customerRepository.saveCustomer(customer.getAddress(), customer.getName(),
                 customer.getPhoneNumber(), customer.getEmployee().getId());
    }

    @Override
    public Customer findCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findCustomerByPhoneNumber(phoneNumber);
    }

    @Override
    public Customer findCustomerById(int id) {
        return customerRepository.findById(id).get();
    }

    @Override
    @Transactional
    public int updateCustomer(Customer customer) {
        return customerRepository.updateCustomer(customer.getAddress(), customer.getName(),
                customer.getPhoneNumber(),customer.getVersion(),customer.getEmployee().getId(), customer.getId());
    }
}
