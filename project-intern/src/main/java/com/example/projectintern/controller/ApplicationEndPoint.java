package com.example.projectintern.controller;

import com.example.projectintern.config.JwtRequestFilter;
import com.example.projectintern.config.JwtTokenUtil;
import com.example.projectintern.dto.ICustomerDetailDTO;
import com.example.projectintern.dto.IProductDetail;
import com.example.projectintern.dto.ServiceStatus;
import com.example.projectintern.dto.account.LoginRequest;
import com.example.projectintern.dto.account.LoginResponse;
import com.example.projectintern.dto.customer.*;
import com.example.projectintern.dto.employee.*;
import com.example.projectintern.dto.orderDetail.*;
import com.example.projectintern.dto.product.*;
import com.example.projectintern.model.*;
import com.example.projectintern.service.*;
import jdk.vm.ci.meta.Local;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.xml.soap.SOAPException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Endpoint
@Validated
public class ApplicationEndPoint {

    private static final String NAMESPACE_URI = "http://interfaces.soap.springboot.vkakarla.com";

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IProductService productService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtRequestFilter requestFilter;

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IOrderDetailService orderDetailService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest request) {
        LoginResponse response = new LoginResponse();
        UserDetails userDetails;
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        userDetails = accountService.loadUserByUsername(request.getUsername());
        String jwt = jwtTokenUtil.generateToken(userDetails);
        response.setJwt(jwt);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEmployeeRequest")
    @ResponsePayload
    @Secured("ROLE_ADMIN")
    public GetAllEmployeeResponse getAllEmployee(@RequestPayload GetAllEmployeeRequest request) {
        if (request.getEmployeePhoneNumber().isEmpty()){
            request.setEmployeePhoneNumber("0");
        }
        List<IEmployeeDTO> employeeList = employeeService.getAll(request.getUsername(),request.getEmployeeName(),request.getEmployeePhoneNumber());
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
    @Secured("ROLE_ADMIN")
    public UpdateEmployeeResponse updateEmployee(@RequestPayload @Valid UpdateEmployeeRequest request) throws SOAPException {
        Employee employee = employeeService.getEmployeeById(request.getEmployeeInfo().getId());
        Account accountCheck = accountService.getAccountByUserName(request.getEmployeeInfo().getAccount().getUsername());
        List<String> errorList = validateUpdateAccount(request);
        //kiểm tra xem phiên bản cập nhật có đúng hay không?
        if (employee.getVersion() != request.getEmployeeInfo().getVersion()) {
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setMessage("Vui long update lai");
            serviceStatus.setStatus("Thất bại");
            UpdateEmployeeResponse response = new UpdateEmployeeResponse();
            response.setServiceStatus(serviceStatus);
            return response;
        }
        // kiểm tra tên đăng nhập có trùng hay không ?
        if (accountCheck != null) {
            if (request.getEmployeeInfo().getAccount().getId() != accountCheck.getId()) {
                errorList.add("Tên đăng nhập không được trùng");
            }
        }
        // kiểm tra validate
        if (errorList.size() > 0) {
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setStatus("Thất bại");
            serviceStatus.setMessage("Vui lòng nhập lại");
            serviceStatus.setError(errorList);
            UpdateEmployeeResponse response = new UpdateEmployeeResponse();
            response.setServiceStatus(serviceStatus);
            return response;
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
            employee1.setAccount(account);
            employee1.setVersion(employee1.getVersion() + 1);
            employeeService.updateEmployee(employee1);
            UpdateEmployeeResponse updateEmployeeResponse = new UpdateEmployeeResponse();
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setStatus("Thành công");
            serviceStatus.setMessage("Cập nhật thành công.");
            updateEmployeeResponse.setServiceStatus(serviceStatus);
            return updateEmployeeResponse;
        }
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
            employee.setAccount(account);
            employeeService.updateEmployee(employee);
            SaveEmployeeResponse saveEmployeeResponse = new SaveEmployeeResponse();
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setMessage("Save successful");
            serviceStatus.setStatus("Success");
            saveEmployeeResponse.setServiceStatus(serviceStatus);
            return saveEmployeeResponse;
        } else {
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setMessage("Vui lòng cập nhật lại");
            serviceStatus.setError(error);
            serviceStatus.setStatus("Failed");
            SaveEmployeeResponse saveEmployeeResponse = new SaveEmployeeResponse();
            saveEmployeeResponse.setServiceStatus(serviceStatus);
            return saveEmployeeResponse;
        }

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductRequest")
    @ResponsePayload
    public GetAllProductResponse getAllProduct(@RequestPayload GetAllProductRequest request) {
        if (request.getCodeProduct() == null) {
            request.setCodeProduct("");
        }
        if (request.getNameProduct() == null) {
            request.setNameProduct("");
        }
//        Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());
//        Page<IProductDetail> productList = productService.getAllProduct(pageable, request.getCodeProduct(), request.getNameProduct());

        int totalRecord = productService.getTotalRecordProduct(request.getCodeProduct(), request.getNameProduct());
        double temp = (double) totalRecord / request.getLimit();
        int totalPages = (int) Math.ceil(temp);

        List<IProductDetail> productList = productService.getAllProductByNameProductAndCodeProduct(
                request.getCodeProduct(), request.getNameProduct(), request.getLimit(), (request.getPage() * request.getLimit()));

        List<ProductInfo> productInfos = new ArrayList<>();

        GetAllProductResponse response = new GetAllProductResponse();
        Supplier<ProductInfo> productInfoSupplier = ProductInfo::new;
        for (IProductDetail product : productList) {
            ProductInfo productInfo = productInfoSupplier.get();
            BeanUtils.copyProperties(product, productInfo);
            productInfos.add(productInfo);
        }
        response.setProducts(productInfos);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveProductRequest")
    @ResponsePayload
    @Secured("ROLE_ADMIN")
    public SaveProductResponse saveProduct(@RequestPayload @Valid SaveProductRequest request) {
        Product productCheckCode = productService.findProductByCodeProduct(request.getProduct().getCodeProduct());
        Product productCheckName = productService.findProductByNameProduct(request.getProduct().getNameProduct());
        SaveProductResponse response = new SaveProductResponse();
        ServiceStatus status = new ServiceStatus();
        if (productCheckName != null) {
            status.setStatus("FAILED");
            status.setMessage("Tên sản phẩm không được trùng");
            response.setServiceStatus(status);
            return response;
        }
        if (productCheckCode != null) {
            status.setStatus("FAILED");
            status.setMessage("Mã sản phẩm không được trùng");
            response.setServiceStatus(status);
            return response;
        }
        Product product = new Product();
        BeanUtils.copyProperties(request.getProduct(), product);
        productService.saveProduct(product);
        status.setStatus("SUCCESS");
        status.setMessage("Thêm mới thành công");
        response.setServiceStatus(status);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductRequest")
    @ResponsePayload
    @Secured("ROLE_ADMIN")
    public UpdateProductResponse updateProduct(@RequestPayload UpdateProductRequest request) throws SOAPException {
        Product product = productService.findProductById(request.getProduct().getId());
        ServiceStatus serviceStatus = new ServiceStatus();
        UpdateProductResponse response = new UpdateProductResponse();
        if (product == null) {
            SOAPException soapException = new SOAPException("Không tìm thấy sản phẩm");
            throw soapException;
        }
        List<Product> productList = productService.findAll();
        for (Product product1 : productList) {
            if (request.getProduct().getNameProduct().equals(product1.getNameProduct())) {
                if (request.getProduct().getId() != product1.getId()) {
                    serviceStatus.setMessage("Không được trùng tên");
                    serviceStatus.setStatus("FAILED");
                    response.setServiceStatus(serviceStatus);
                    return response;
                }
            }
            if (request.getProduct().getCodeProduct().equals(product1.getCodeProduct())) {
                if (request.getProduct().getId() != product1.getId()) {
                    serviceStatus.setStatus("FAILED");
                    response.setServiceStatus(serviceStatus);
                    return response;
                }
            }
        }
        if (request.getProduct().getVersion() != product.getVersion()) {
            serviceStatus.setMessage("Vui lòng cập nhật lại");
            serviceStatus.setStatus("FAILED");
            response.setServiceStatus(serviceStatus);
            return response;
        }
        Product productResult = new Product();
        BeanUtils.copyProperties(request.getProduct(), productResult);
        productResult.setVersion(request.getProduct().getVersion() + 1);
        productService.saveProduct(productResult);
        serviceStatus.setMessage("Cập nhật thành công");
        serviceStatus.setStatus("SUCCESS");
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCustomerRequest")
    @ResponsePayload
    public GetAllCustomerResponse getAllCustomer(@RequestPayload GetAllCustomerRequest request) {
        if (request.getNameCustomer() == null) {
            request.setNameCustomer("");
        }
        if (request.getPhoneNumber() == null) {
            request.setPhoneNumber("0");
        }
//        Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());
//        Page<ICustomerDetailDTO> customerDetailDTOS = customerService.findCustomerByNameAndPhoneNumber(pageable,
//                                                                    request.getNameCustomer(), request.getPhoneNumber());
//
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
    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "saveCustomerRequest")
    @ResponsePayload
    public SaveCustomerResponse saveCustomer(@RequestPayload SaveCustomerRequest request){
        Customer customerCheck = customerService.findCustomerByPhoneNumber(request.getCustomerInfo().getPhoneNumber());
        ServiceStatus serviceStatus = new ServiceStatus();
        SaveCustomerResponse response = new SaveCustomerResponse();
        if (customerCheck != null){
            serviceStatus.setStatus("FALSE");
            serviceStatus.setMessage("Số điện thoại đã bị trùng, vui lòng nhập số khác");
            response.setServiceStatus(serviceStatus);
            return response;
        }else {
            Employee employee = employeeService.getEmployeeByAccountUsername(requestFilter.getUserNameToken());
            Customer customer = new Customer();
            BeanUtils.copyProperties(request.getCustomerInfo(),customer);
            customer.setEmployee(employee);
            Customer customerSave = customerService.saveCustomer(customer);
            if (customerSave != null){
                serviceStatus.setStatus("TRUE");
                response.setServiceStatus(serviceStatus);
                return response;
            }else {
                serviceStatus.setStatus("FALSE");
                response.setServiceStatus(serviceStatus);
                return response;
            }
        }

    }

    @PayloadRoot(namespace = NAMESPACE_URI,localPart ="updateCustomerRequest")
    @ResponsePayload
    public UpdateCustomerResponse updateCustomer(@RequestPayload UpdateCustomerRequest request) {
        Customer customerCheck = customerService.findCustomerByPhoneNumber(request.getCustomerInfo().getPhoneNumber());
        UpdateCustomerResponse response = new UpdateCustomerResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Customer customerMain = customerService.findCustomerById(request.getCustomerInfo().getId());
        if (customerCheck != null) {
            if (customerCheck.getId() != request.getCustomerInfo().getId()) {
                serviceStatus.setStatus("FALSE");
                serviceStatus.setMessage("Số điện thoại đã bị trùng, vui lòng cập nhật lại");
                response.setServiceStatus(serviceStatus);
                return response;
            }
        }
        if (customerMain.getVersion() != request.getCustomerInfo().getVersion()){
            serviceStatus.setStatus("FALSE");
            serviceStatus.setMessage("Vui lòng cập nhật lại phiên bản cập nhật");
            response.setServiceStatus(serviceStatus);
            return response;
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(request.getCustomerInfo(), customer);
            customer.setVersion(customer.getVersion()+1);
            Employee employee = employeeService.getEmployeeByAccountUsername(requestFilter.getUserNameToken());
            customer.setEmployee(employee);
            Customer customerUpdated = customerService.saveCustomer(customer);
            if (customerUpdated != null) {
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
    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getOrderDetailByAdminRequest")
    @ResponsePayload
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public GetOrderDetailResponse getOrderDetailByAdmin(@RequestPayload GetOrderDetailByAdminRequest request){
        if (request.getDateEnd().isEmpty()){
            LocalDate dateMax = orderDetailService.getMaxDateStart();
            request.setDateEnd(String.valueOf(dateMax));
        }
        if (request.getDateStart().isEmpty()){
            LocalDate dateMin = orderDetailService.getMinDateEnd();
            request.setDateStart(String.valueOf(dateMin));
        }
        if (request.getCodeProduct().isEmpty()){
            request.setCodeProduct("PR-");
        }
        if (request.getCustomerPhoneNumber().isEmpty()){
            request.setCustomerPhoneNumber("0");
        }
        Employee employee = employeeService.getEmployeeByAccountUsername(requestFilter.getUserNameToken());
        Supplier<OrderDTO> orderDTOSupplier = OrderDTO::new;
        List<OrderDTO> orderDTOList = new ArrayList<>();
        if (employee.getAccount().getRole().getName().equals("ROLE_ADMIN")){
            List<IOrderDetailDTO> orderDetailDTOList = orderDetailService.getOrderDetailByAdmin(request.getAccountName(),
                    request.getEmployeeName(),request.getCodeProduct(),request.getCustomerName(),request.getCustomerPhoneNumber(),
                    request.getDateStart(),request.getDateEnd());
            for (IOrderDetailDTO orderDetailDTO:orderDetailDTOList) {
                OrderDTO orderDTO = orderDTOSupplier.get();
                orderDTO.setDateStart(orderDetailDTO.getDateStart());
                orderDTO.setCodeProduct(orderDetailDTO.getCodeProduct());
                orderDTO.setPriceBuy(orderDetailDTO.getPriceBuy());
                orderDTO.setQuantityBook(orderDetailDTO.getQuantityBook());
                orderDTO.setCustomerName(orderDetailDTO.getCustomerName());
                orderDTO.setCustomerPhoneNumber(orderDetailDTO.getCustomerPhoneNumber());
                orderDTO.setCustomerAddress(orderDetailDTO.getCustomerAddress());
                orderDTO.setStatusName(orderDetailDTO.getStatusName());
                orderDTO.setAccountName(orderDetailDTO.getAccountName());
                orderDTO.setEmployeeName(orderDetailDTO.getEmployeeName());
                orderDTO.setDateAllocation(orderDetailDTO.getDateAllocation());
                orderDTOList.add(orderDTO);
            }
        }else {
            List<IOrderDetailDTO> orderDetailDTOList = orderDetailService.getOrderDetailByUser(employee.getId(),request.getCodeProduct(),
                    request.getCustomerName(), request.getCustomerPhoneNumber(), request.getDateStart(), request.getDateEnd());
            for (IOrderDetailDTO orderDetailDTO: orderDetailDTOList) {
                OrderDTO orderDTO = orderDTOSupplier.get();
                orderDTO.setDateStart(orderDetailDTO.getDateStart());
                orderDTO.setPriceBuy(orderDetailDTO.getPriceBuy());
                orderDTO.setCodeProduct(orderDetailDTO.getCodeProduct());
                orderDTO.setQuantityBook(orderDetailDTO.getQuantityBook());
                orderDTO.setCustomerName(orderDetailDTO.getCustomerName());
                orderDTO.setCustomerPhoneNumber(orderDetailDTO.getCustomerPhoneNumber());
                orderDTO.setCustomerAddress(orderDetailDTO.getCustomerAddress());
                orderDTO.setStatusName(orderDetailDTO.getStatusName());
                orderDTO.setDateAllocation(orderDetailDTO.getDateAllocation());
                orderDTOList.add(orderDTO);
            }
        }
        GetOrderDetailResponse response = new GetOrderDetailResponse();
        response.setOrderDetailDTOs(orderDTOList);
        return response;
    }

    public List<String> validateSaveOrder(SaveOrderDetailRequest request){
        List<String> errorList = new ArrayList<>();
        Product productByCodeProduct = productService.findProductByCodeProduct(request.getOrderDetail().getProduct().getCodeProduct());
        Product productByNameProduct = productService.findProductByNameProduct(request.getOrderDetail().getProduct().getNameProduct());
        if (!request.getOrderDetail().getProduct().getCodeProduct().matches("^PR-\\d{4}$")){
            errorList.add("Vui lòng nhập đúng mã sản phẩm");
        }else if (productByCodeProduct == null){
            errorList.add("Không tồn tại sản phẩm");
        }
        if (request.getOrderDetail().getProduct().getCodeProduct().isEmpty() || request.getOrderDetail().getProduct().getNameProduct().isEmpty()) {
            errorList.add("Không được để trống");
        }
        if (request.getOrderDetail().getQuantityBook() <= 0){
            errorList.add("Số lượng đặt hàng không hợp lệ, vui lòng đặt lại");
        }
        if (productByNameProduct == null){
            errorList.add("Tên sản phẩm không tồn tại");
        }
        return errorList;
    }



    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "saveOrderDetailRequest")
    @ResponsePayload
    public SaveOrderDetailResponse saveOrderDetail(@RequestPayload SaveOrderDetailRequest request){
        List<String> errorList = validateSaveOrder(request);
        SaveOrderDetailResponse response = new SaveOrderDetailResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        if(errorList.size() != 0){
            serviceStatus.setStatus("FALSE");
            serviceStatus.setError(errorList);
            response.setServiceStatus(serviceStatus);
        }else {
            OrderDetail orderDetail = new OrderDetail();
            Employee employee = new Employee();
            Product product = new Product();
            Customer customer = new Customer();
            Status status = new Status();
            BeanUtils.copyProperties(request.getOrderDetail().getProduct(),product);
            BeanUtils.copyProperties(request.getOrderDetail().getCustomer(),customer);
            BeanUtils.copyProperties(request.getOrderDetail().getStatus(),status);
            BeanUtils.copyProperties(request.getOrderDetail().getEmployee(),employee);
            orderDetail.setStatus(status);
            orderDetail.setCustomer(customer);
            orderDetail.setProduct(product);
            orderDetail.setEmployee(employee);
            orderDetail.setQuantityBook(request.getOrderDetail().getQuantityBook());
            orderDetail.setDateStart(LocalDate.parse(request.getOrderDetail().getDateStart()));
            orderDetail.setDateAllocation(LocalDate.parse(request.getOrderDetail().getDateAllocation()));
            orderDetail.setPrice(request.getOrderDetail().getQuantityBook() * request.getOrderDetail().getProduct().getPriceSell());
            OrderDetail orderDetailCheck = orderDetailService.saveOrderDetail(orderDetail);
            if (orderDetailCheck == null){
                serviceStatus.setMessage("Hệ thống bị lỗi, vui lòng thử lại");
                serviceStatus.setStatus("FALSE");
                response.setServiceStatus(serviceStatus);
            }else {
                serviceStatus.setStatus("TRUE");
                response.setServiceStatus(serviceStatus);
            }
        }
        return response;
    }
}
