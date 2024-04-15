package com.example.springmvc.dto;

import com.example.springmvc.model.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CustomerDTO implements Validator {

    private  int id;
    private String name;
    private String phoneNumber;
    private String address;
    private int version;
    private Employee employee;

    public CustomerDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDTO customerDTO = (CustomerDTO) target;
        if (customerDTO.getPhoneNumber().isEmpty()){
            errors.rejectValue("phoneNumber",null,"Số điện thooại không được để trống");
        }else if (!customerDTO.getPhoneNumber().matches("^0[\\d]{9}$")){
            errors.rejectValue("phoneNumber",null,"Số điện thoại không đúng định dạng.");
        }
        if (customerDTO.getName().isEmpty()){
            errors.rejectValue("name",null,"Tên khách hàng không được để trống");
        }
        if (customerDTO.getAddress().isEmpty()){
            errors.rejectValue("address",null,"Địa chỉ không được để trống");
        }
    }
}
