package com.example.projectintern.controller;

import com.example.projectintern.config.JwtRequestFilter;
import com.example.projectintern.dto.OrderDetailInfo;
import com.example.projectintern.dto.ServiceStatus;
import com.example.projectintern.dto.orderDetail.*;
import com.example.projectintern.model.Customer;
import com.example.projectintern.model.Employee;
import com.example.projectintern.model.Product;
import com.example.projectintern.service.ICustomerService;
import com.example.projectintern.service.IEmployeeService;
import com.example.projectintern.service.IOrderDetailService;
import com.example.projectintern.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Endpoint
public class OrderEndPoint {

    private static final String ADMIN = "ROLE_ADMIN";
    private static final String USER = "ROLE_USER";
    private static final String NAMESPACE_URI = "http://interfaces.soap.springboot.vkakarla.com";
    @Autowired
    private IOrderDetailService orderDetailService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private JwtRequestFilter requestFilter;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICustomerService customerService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderDetailByAdminRequest")
    @ResponsePayload
    @Secured({ADMIN, USER})
    public GetOrderDetailResponse getOrderDetailByAdmin(@RequestPayload GetOrderDetailByAdminRequest request) {
        if (request.getDateEnd().isEmpty()) {
            request.setDateEnd("9999-12-31");
        }
        if (request.getDateStart().isEmpty()) {
            LocalDate dateMin = orderDetailService.getMinDateEnd();
            request.setDateStart("2000-12-31");
        }


        Employee employee = employeeService.getEmployeeByAccountUsername(requestFilter.getUserNameToken());
        int countRecordOrderRequest = orderDetailService.countRecordByOrderRequest(request.getAccountName(),
                request.getEmployeeName(), request.getCodeProduct(), request.getCustomerName(), request.getCustomerPhoneNumber(),
                request.getDateStart(), request.getDateEnd(), employee.getAccount().getRole().getName().equals("ROLE_ADMIN"), employee.getId());

        Supplier<OrderDTO> orderDTOSupplier = OrderDTO::new;
        List<OrderDTO> orderDTOList = new ArrayList<>();
        List<IOrderDetailDTO> orderDetailDTOList = orderDetailService.getOrderDetailByAdmin(request.getAccountName(),
                request.getEmployeeName(), request.getCodeProduct(), request.getCustomerName(), request.getCustomerPhoneNumber(),
                request.getDateStart(), request.getDateEnd(), employee.getAccount().getRole().getName().equals("ROLE_ADMIN"),
                employee.getId(), request.getLimit(), (request.getPage() - 1) * request.getLimit());
        for (IOrderDetailDTO orderDetailDTO : orderDetailDTOList) {
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
        GetOrderDetailResponse response = new GetOrderDetailResponse();
        response.setOrderDetailDTOs(orderDTOList);
        return response;
    }

    public List<String> validateSaveOrder(OrderDetailInfo orderDetailInfo) {
        List<String> errorList = new ArrayList<>();
        Product productByCodeProduct = productService.findProductByCodeProduct(orderDetailInfo.getCodeProduct());
        Customer customerPhoneNumber = customerService.findCustomerByPhoneNumber(orderDetailInfo.getCustomerPhoneNumber());
        if (!orderDetailInfo.getCodeProduct().matches("^PR-\\d{4}$")) {
            errorList.add("Vui lòng nhập đúng mã sản phẩm");
        } else if (productByCodeProduct == null) {
            errorList.add("Không tồn tại sản phẩm");
        }
        if (orderDetailInfo.getQuantityBook() <= 0) {
            errorList.add("Số lượng đặt hàng không hợp lệ, vui lòng đặt lại");
        }
        if (customerPhoneNumber == null) {
            errorList.add("Không tồn tại khách hàng trong hệ thống");
        }
        return errorList;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveOrderDetailRequest")
    @ResponsePayload
    @Secured({ADMIN,USER})
    @Transactional
    public SaveOrderDetailResponse saveOrderDetail(@RequestPayload SaveOrderDetailRequest request) throws Exception {
        SaveOrderDetailResponse response = new SaveOrderDetailResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<OrderDetailInfo> orderDetailInfoList = request.getOrderDetail();
        for (OrderDetailInfo orderDetailInfo : orderDetailInfoList) {
            if (orderDetailInfo.getId() == 0) {
                List<String> errorList = validateSaveOrder(orderDetailInfo);
                if (errorList.size() == 0) {
                    Customer customer = customerService.findCustomerByPhoneNumber(orderDetailInfo.getCustomerPhoneNumber());
                    Product product = productService.findProductByCodeProduct(orderDetailInfo.getCodeProduct());
                    Employee employee = employeeService.getEmployeeByAccountUsername(requestFilter.getUserNameToken());
                    int orderSaved = orderDetailService.saveOrder(String.valueOf(LocalDate.now()), orderDetailInfo.getQuantityBook(),
                            customer.getId(), employee.getId(), product.getId(), 1, orderDetailInfo.getPrice());
                    if (orderSaved != 1) {
                        throw new RuntimeException("Không lưu được đối tượng" + orderDetailInfo.toString());
                    } else {
                        serviceStatus.setStatus("TRUE");
                    }
                } else {
                    throw new RuntimeException("Dữ liệu nhâp vào không hợp lệ," + errorList.toString() + orderDetailInfo.toString());
                }
            } else {
                List<String> errorList = validateUpdateOrder(orderDetailInfo);
                if (errorList.size() == 0) {
                    Customer customer = customerService.findCustomerByPhoneNumber(orderDetailInfo.getCustomerPhoneNumber());
                    Product product = productService.findProductByCodeProduct(orderDetailInfo.getCodeProduct());
                    Employee employee = employeeService.getEmployeeByAccountUsername(requestFilter.getUserNameToken());
                    int orderUpdated = orderDetailService.updateOrder(orderDetailInfo.getDateStart(), orderDetailInfo.getQuantityBook(),
                            customer.getId(), employee.getId(), product.getId(), orderDetailInfo.getPrice(), orderDetailInfo.getId());
                    if (orderUpdated != 1) {
                        throw new RuntimeException("Không cập nhật được đối tượng" + orderDetailInfo.toString());
                    } else {
                        serviceStatus.setStatus("TRUE");
                    }
                } else {
                    throw new RuntimeException("Dữ liệu nhập vào không hợp lệ," + errorList.toString() + orderDetailInfo.toString());
                }
            }
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }

    public List<String> validateUpdateOrder(OrderDetailInfo orderDetailInfo) {
        List<String> errorList = new ArrayList<>();
        Product product = productService.findProductByCodeProduct(orderDetailInfo.getCodeProduct());
        Customer customer = customerService.findCustomerByPhoneNumber(orderDetailInfo.getCustomerPhoneNumber());
        if (!orderDetailInfo.getCodeProduct().matches("^PR-\\d{4}$")) {
            errorList.add("Mã sản phẩm không hợp lệ");
        } else if (product == null) {
            errorList.add("Không tồn tại sản phẩm ");
        }
        if (customer == null) {
            errorList.add("Không tồn tại khách hàng");
        }
        if (orderDetailInfo.getQuantityBook() <= 0) {
            errorList.add("Số lượng đặt hàng không hợp lệ");
        }
        return errorList;
    }

    public List<String> validateImportProduct(AllocationRequest request) {
        List<String> errorList = new ArrayList<>();
        if (!request.getProductCode().matches("^PR-\\d{4}$")) {
            errorList.add("Mã sản phẩm không đúng định dạng");
        }
        if (request.getQuantity() <= 0) {
            errorList.add("Số lượng hàng không hợp lệ, vui lòng nhập lại");
        }
        return errorList;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "allocationRequest")
    @ResponsePayload
    @Secured({ADMIN})
    public AllocationResponse allocationProduct(@RequestPayload AllocationRequest request) {
        ServiceStatus status = new ServiceStatus();
        AllocationResponse response = new AllocationResponse();
        List<String> errorList = new ArrayList<>();
        if (errorList.size() > 0) {
            status.setError(errorList);
            status.setStatus("FLASE");
        } else {
            Product product = productService.findProductByCodeProduct(request.getProductCode());
            orderDetailService.importProduct(product.getId(), request.getQuantity());
            status.setStatus("TRUE");
        }
        response.setServiceStatus(status);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "customerNotBuyRequest")
    @ResponsePayload
    @Secured({ADMIN})
    public CustomerNotBuyResponse getCustomerNotBuy(@RequestPayload CustomerNotBuyRequest request) {
        if (request.getAnalystRequest().getDateStart().isEmpty()) {
            request.getAnalystRequest().setDateStart("2024-01-01");
        }
        if (request.getAnalystRequest().getDateEnd().isEmpty()) {
            request.getAnalystRequest().setDateEnd("2024-10-10");
        }
        List<ICustomerNoOrderDTO> customerNoOrderList = orderDetailService.getListCustomerNoOrder(LocalDate.parse(request.getAnalystRequest().getDateStart()),
                LocalDate.parse(request.getAnalystRequest().getDateEnd()), request.getAnalystRequest().getLimit(),
                ((request.getAnalystRequest().getPage() - 1) * request.getAnalystRequest().getLimit()));
        List<CustomerNoOrder> customerNoOrders = new ArrayList<>();
        Supplier<CustomerNoOrder> customerNoOrderSupplier = CustomerNoOrder::new;
        for (ICustomerNoOrderDTO customerNoOrderDTO : customerNoOrderList) {
            CustomerNoOrder customerNoOrder = customerNoOrderSupplier.get();
            customerNoOrder.setCustomerId(customerNoOrderDTO.getCustomerId());
            customerNoOrder.setCustomerName(customerNoOrderDTO.getCustomerName());
            customerNoOrder.setCustomerPhoneNumber(customerNoOrderDTO.getCustomerPhoneNumber());
            customerNoOrder.setCustomerAddress(customerNoOrderDTO.getCustomerAddress());
            customerNoOrders.add(customerNoOrder);
        }
        CustomerNotBuyResponse response = new CustomerNotBuyResponse();
        response.setCustomersNoOrder(customerNoOrders);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "productAnalystBestSellerRequest")
    @ResponsePayload
    @Secured({ADMIN})
    public ProductAnalystResponse getListProductBestSeller(@RequestPayload ProductAnalystBestSellerRequest request) {
        if (request.getAnalyst().getDateStart().equals("")) {
            request.getAnalyst().setDateStart("2020-01-01");
        }
        if (request.getAnalyst().getDateEnd().equals("")) {
            request.getAnalyst().setDateEnd("2025-01-01");
        }
        List<IProductAnalystDTO> productAnalystDTOList = orderDetailService.getListProductBestSeller(LocalDate.parse(request.getAnalyst().getDateStart()),
                LocalDate.parse(request.getAnalyst().getDateEnd()), request.getAnalyst().getLimit(), ((request.getAnalyst().getPage() - 1) * request.getAnalyst().getLimit()));
        List<ProductAnalyst> productAnalystList = new ArrayList<>();
        Supplier<ProductAnalyst> productAnalystSupplier = ProductAnalyst::new;
        for (IProductAnalystDTO productAnalystTemp : productAnalystDTOList) {
            ProductAnalyst productAnalyst = productAnalystSupplier.get();
            BeanUtils.copyProperties(productAnalystTemp, productAnalyst);
            productAnalystList.add(productAnalyst);
        }
        ProductAnalystResponse response = new ProductAnalystResponse();
        response.setProductAnalyst(productAnalystList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "productAnalystNoSellRequest")
    @ResponsePayload
    @Secured({ADMIN})
    public ProductAnalystResponse getListProductNoSell(@RequestPayload ProductAnalystNoSellRequest request){
        if (request.getAnalyst().getDateStart().equals("")) {
            request.getAnalyst().setDateStart("2020-01-01");
        }
        if (request.getAnalyst().getDateEnd().equals("")) {
            request.getAnalyst().setDateEnd("2025-01-01");
        }
        List<IProductAnalystDTO> productAnalystDTOList = orderDetailService.getListProductNoSeller(LocalDate.parse(request.getAnalyst().getDateStart()),
                LocalDate.parse(request.getAnalyst().getDateEnd()), request.getAnalyst().getLimit(), ((request.getAnalyst().getPage() - 1) * request.getAnalyst().getLimit()));
        List<ProductAnalyst> productAnalystList = new ArrayList<>();
        Supplier<ProductAnalyst> productAnalystSupplier = ProductAnalyst::new;
        for (IProductAnalystDTO productAnalystTemp : productAnalystDTOList) {
            ProductAnalyst productAnalyst = productAnalystSupplier.get();
            productAnalyst.setNameProduct(productAnalystTemp.getNameProduct());
            productAnalyst.setCodeProduct(productAnalystTemp.getCodeProduct());
            productAnalyst.setProductId(productAnalystTemp.getProductId());
            productAnalystList.add(productAnalyst);
        }
        ProductAnalystResponse response = new ProductAnalystResponse();
        response.setProductAnalyst(productAnalystList);
        return response;
    }

}
