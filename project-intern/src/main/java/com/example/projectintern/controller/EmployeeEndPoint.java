package com.example.projectintern.controller;

import com.example.projectintern.dto.ServiceStatus;
import com.example.projectintern.dto.employee.*;
import com.example.projectintern.model.Account;
import com.example.projectintern.model.Employee;
import com.example.projectintern.model.Role;
import com.example.projectintern.service.IAccountService;
import com.example.projectintern.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.xml.soap.SOAPException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Endpoint
public class EmployeeEndPoint {
    private static final String NAMESPACE_URI = "http://interfaces.soap.springboot.vkakarla.com";


    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IAccountService accountService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEmployeeRequest")
    @ResponsePayload
    @Secured("ROLE_ADMIN")
    public GetAllEmployeeResponse getAllEmployee(@RequestPayload GetAllEmployeeRequest request) {
        if (request.getEmployeePhoneNumber().isEmpty()) {
            request.setEmployeePhoneNumber("0");
        }
        List<IEmployeeDTO> employeeList = employeeService.getAll(request.getUsername(), request.getEmployeeName(), request.getEmployeePhoneNumber());
        List<EmployeeDTO> employeeInfoList = new ArrayList<>();
        Supplier<EmployeeDTO> employeeInfoSupplier = EmployeeDTO::new;
        for (IEmployeeDTO employee : employeeList) {
            EmployeeDTO employeeDTO = employeeInfoSupplier.get();
            employeeDTO.setEmployeeName(employee.getEmployeeName());
            employeeDTO.setUsernameEmployee(employee.getUsernameEmployee());
            employeeDTO.setEmployeePhoneNumber(employee.getEmployeePhoneNumber());
            employeeInfoList.add(employeeDTO);
        }
        GetAllEmployeeResponse response = new GetAllEmployeeResponse();
        response.setEmployees(employeeInfoList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    @Transactional
    @Secured("ROLE_ADMIN")
    public UpdateEmployeeResponse updateEmployee(@RequestPayload @Valid UpdateEmployeeRequest request) throws SOAPException {
        Account accountCheck = accountService.getAccountByUserName(request.getEmployeeInfo().getAccount().getUsername());
        List<String> errorList = validateUpdateAccount(request);
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        if (accountCheck != null) {
            if (request.getEmployeeInfo().getAccount().getId() != accountCheck.getId()) {
                errorList.add("Tên đăng nhập không được trùng");
            }
        }
        // kiểm tra validate
        if (errorList.size() > 0) {
//            serviceStatus.setStatus("Thất bại");
//            serviceStatus.setMessage("Vui lòng nhập lại");
//            serviceStatus.setError(errorList);
//            response.setServiceStatus(serviceStatus);
//            return response;

            throw new SOAPException(errorList.toString());
        } else {
            Employee employee1 = new Employee();
            BeanUtils.copyProperties(request.getEmployeeInfo(), employee1);
            Account account = new Account();
            account.setId(request.getEmployeeInfo().getAccount().getId());
            account.setUsername(request.getEmployeeInfo().getAccount().getUsername());
            account.setPassword(request.getEmployeeInfo().getAccount().getPassword());
            Role role = new Role();
            role.setId(request.getEmployeeInfo().getAccount().getRole().getId());
            role.setName(request.getEmployeeInfo().getAccount().getRole().getName());
            account.setRole(role);
            int accountCheckUpdate = accountService.updateAccount(account.getUsername(), account.getPassword(),account.getRole().getId(),account.getId());
            employee1.setAccount(account);
            int employeeUpdate = employeeService.updateEmployeeVersion(employee1);
            if (employeeUpdate != 0) {
                serviceStatus.setStatus("TRUE");
                response.setServiceStatus(serviceStatus);
                return response;
            } else {
//                serviceStatus.setStatus("FALSE");
//                serviceStatus.setMessage("Vui lòng cập nhật lại.");
//                response.setServiceStatus(serviceStatus);
//                return response;
                throw new RuntimeException("Please update again");
            }
        }
    }
    public List<String> validateUpdateAccount(UpdateEmployeeRequest request) {
        List<String> errorList = new ArrayList<>();
        if (request.getEmployeeInfo().getAccount().getUsername().isEmpty()) {
            errorList.add("Tên không được để trống");
        }
        if (!request.getEmployeeInfo().getAccount().getPassword().matches("^(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            errorList.add("Mật khẩu phải có ít nhất 8 kí tự bắt đầu bằng chữ viết hoa");
        } else if (request.getEmployeeInfo().getAccount().getPassword().isEmpty()) {
            errorList.add("Mật khẩu không được để trống");
        }
        if (!request.getEmployeeInfo().getAccount().getPassword().equals(request.getConfirmPassword())) {
            errorList.add("Xác nhận mật khẩu không trùng khớp");
        }
        if (request.getEmployeeInfo().getName().isEmpty()) {
            errorList.add("Tên nhân viên không được để trống");
        }
        if (request.getEmployeeInfo().getPhoneNumber().isEmpty()) {
            errorList.add("Số điện thoại không được để trống");
        }
        return errorList;
    }

    public List<String> validateAccount(SaveEmployeeRequest request) {
        List<String> error = new ArrayList<>();
        Account accountCheck = accountService.getAccountByUserName(request.getEmployeeInfo().getAccount().getUsername());
        if (request.getEmployeeInfo().getAccount().getUsername().isEmpty()) {
            error.add("Tên không được để trống");
        } else if (accountCheck != null) {
            error.add("Tên đăng nhập đã bị trùng");
        }
        if (!request.getEmployeeInfo().getAccount().getPassword().matches("^(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            error.add("Mật khẩu phải có ít nhất 8 kí tự bắt đầu bằng chữ viết hoa");
        } else if (request.getEmployeeInfo().getAccount().getPassword().isEmpty()) {
            error.add("Mật khẩu không được để trống");
        }
        if (!request.getEmployeeInfo().getAccount().getPassword().equals(request.getConfirmPassword())) {
            error.add("Xác nhận mật khẩu không trùng khớp");
        }
        if (request.getEmployeeInfo().getName().isEmpty()) {
            error.add("Tên nhân viên không được để trống");
        }
        if (request.getEmployeeInfo().getPhoneNumber().isEmpty()) {
            error.add("Số điện thoại không được để trống");
        }
        return error;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveEmployeeRequest")
    @ResponsePayload
    @Transactional
    @Secured("ROLE_ADMIN")
    public SaveEmployeeResponse saveEmployee(@RequestPayload SaveEmployeeRequest request) throws SOAPException {
        List<String> error = validateAccount(request);
        if (error.size() == 0) {
            Account account = new Account();
            account.setUsername(request.getEmployeeInfo().getAccount().getUsername());
            account.setPassword(request.getEmployeeInfo().getAccount().getPassword());
            Role role = new Role();
            role.setId(request.getEmployeeInfo().getAccount().getRole().getId());
            account.setRole(role);
            accountService.saveAccount(account);
            Employee employee = new Employee();
            BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
            account.setId(accountService.getAccountByUsername(request.getEmployeeInfo().getAccount().getUsername()));
            employee.setAccount(account);
            employeeService.updateEmployee(employee);
            SaveEmployeeResponse saveEmployeeResponse = new SaveEmployeeResponse();
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setStatus("TRUE");
            saveEmployeeResponse.setServiceStatus(serviceStatus);
            return saveEmployeeResponse;
        } else {
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setMessage("Vui lòng cập nhật lại");
            serviceStatus.setError(error);
            serviceStatus.setStatus("FALSE");
            SaveEmployeeResponse saveEmployeeResponse = new SaveEmployeeResponse();
            saveEmployeeResponse.setServiceStatus(serviceStatus);
            return saveEmployeeResponse;
        }

    }

}
