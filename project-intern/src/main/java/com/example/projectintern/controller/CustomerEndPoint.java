package com.example.projectintern.controller;

import com.example.projectintern.config.JwtRequestFilter;
import com.example.projectintern.dto.ICustomerDetailDTO;
import com.example.projectintern.dto.ServiceStatus;
import com.example.projectintern.dto.customer.*;
import com.example.projectintern.dto.employee.EmployeeInfo;
import com.example.projectintern.model.Customer;
import com.example.projectintern.model.Employee;
import com.example.projectintern.service.ICustomerService;
import com.example.projectintern.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Endpoint
public class CustomerEndPoint {

    private static final String NAMESPACE_URI = "http://interfaces.soap.springboot.vkakarla.com";

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private JwtRequestFilter requestFilter;

    @Autowired
    private IEmployeeService employeeService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCustomerRequest")
    @ResponsePayload
    public GetAllCustomerResponse getAllCustomer(@RequestPayload GetAllCustomerRequest request) {
        if (request.getNameCustomer() == null) {
            request.setNameCustomer("");
        }
        if (request.getPhoneNumber() == null) {
            request.setPhoneNumber("0");
        }
        int totalRowCustomer = customerService.totalRowCustomer(request.getNameCustomer(), request.getPhoneNumber());
        long temp = totalRowCustomer / request.getLimit();
        int totalPage = (int) Math.ceil(temp);
        List<ICustomerDetailDTO> customerDetailDTOList = customerService.getCustomerByNameAndPhoneNumber(request.getNameCustomer(),
                request.getPhoneNumber(), request.getLimit(), (request.getLimit() * request.getPage()));

        Supplier<CustomerInfo> customerInfoSupplier = CustomerInfo::new;
        Supplier<EmployeeInfo> employeeInfoSupplier = EmployeeInfo::new;
        List<CustomerInfo> customerInfoList = new ArrayList<>();
        for (ICustomerDetailDTO customerDetailDTO : customerDetailDTOList) {
            CustomerInfo customerInfo = customerInfoSupplier.get();
            EmployeeInfo employeeInfo = employeeInfoSupplier.get();
            customerInfo.setAddress(customerDetailDTO.getAddress());
            customerInfo.setFlag(customerDetailDTO.getFlag());
            customerInfo.setName(customerDetailDTO.getName());
            customerInfo.setId(customerDetailDTO.getId());
            customerInfo.setVersion(customerDetailDTO.getVersion());
            customerInfo.setPhoneNumber(customerDetailDTO.getPhoneNumber());
            employeeInfo.setName(customerDetailDTO.getNameEmployee());
            employeeInfo.setId(customerDetailDTO.getIdEmployee());
            employeeInfo.setPhoneNumber(customerDetailDTO.getPhoneNumberEmployee());
            customerInfo.setEmployee(employeeInfo);
            customerInfoList.add(customerInfo);
        }
        GetAllCustomerResponse response = new GetAllCustomerResponse();
        response.setCustomers(customerInfoList);
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveCustomerRequest")
    @ResponsePayload
    @Secured({"ROLE_USER, ROLE_ADMIN"})
    public SaveCustomerResponse saveCustomer(@RequestPayload SaveCustomerRequest request) {
        Customer customerCheck = customerService.findCustomerByPhoneNumber(request.getCustomerInfo().getPhoneNumber());
        ServiceStatus serviceStatus = new ServiceStatus();
        SaveCustomerResponse response = new SaveCustomerResponse();
        if (customerCheck != null) {
            serviceStatus.setStatus("FALSE");
            serviceStatus.setMessage("Số điện thoại đã bị trùng, vui lòng nhập số khác");
            response.setServiceStatus(serviceStatus);
            return response;
        } else {
            Employee employee = employeeService.getEmployeeByAccountUsername(requestFilter.getUserNameToken());
            Customer customer = new Customer();
            BeanUtils.copyProperties(request.getCustomerInfo(), customer);
            customer.setEmployee(employee);
            int customerSave = customerService.saveCustomer(customer);
            if (customerSave == 1) {
                serviceStatus.setStatus("TRUE");
                response.setServiceStatus(serviceStatus);
                return response;
            } else {
                serviceStatus.setStatus("FALSE");
                response.setServiceStatus(serviceStatus);
                return response;
            }
        }

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCustomerRequest")
    @ResponsePayload
    @Secured({"ROLE_USER,ROLE_ADMIN"})
    public UpdateCustomerResponse updateCustomer(@RequestPayload UpdateCustomerRequest request) {
        Customer customerCheck = customerService.findCustomerByPhoneNumber(request.getCustomerInfo().getPhoneNumber());
        UpdateCustomerResponse response = new UpdateCustomerResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        if (customerCheck != null) {
            if (customerCheck.getId() != request.getCustomerInfo().getId()) {
                serviceStatus.setStatus("FALSE");
                serviceStatus.setMessage("Số điện thoại đã bị trùng, vui lòng cập nhật lại");
                response.setServiceStatus(serviceStatus);
                return response;
            }
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(request.getCustomerInfo(), customer);
        Employee employee = employeeService.getEmployeeByAccountUsername(requestFilter.getUserNameToken());
        customer.setEmployee(employee);
        int customerUpdated = customerService.updateCustomer(customer);
        if (customerUpdated == 1) {
            serviceStatus.setStatus("TRUE");
            response.setServiceStatus(serviceStatus);
            return response;
        } else {
            serviceStatus.setStatus("FALSE");
            serviceStatus.setMessage("Đối tượng chưa được cập nhật. Vui lòng thử lại");
            response.setServiceStatus(serviceStatus);
            return response;
        }



    }


}
