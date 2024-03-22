package com.example.projectintern.service;

import com.example.projectintern.dto.orderDetail.AnalystRequest;
import com.example.projectintern.dto.orderDetail.ICustomerNoOrderDTO;
import com.example.projectintern.dto.orderDetail.IOrderDetailDTO;
import com.example.projectintern.dto.orderDetail.IProductAnalystDTO;
import com.example.projectintern.model.OrderDetail;
import com.example.projectintern.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderRepository.save(orderDetail);
    }

    @Override
    public List<IOrderDetailDTO> getOrderDetailByAdmin(String accountName, String employeeName, String codeProduct,
                                                       String customerName, String customerPhoneNumber, String dateStart,
                                                       String dateEnd,boolean isAdmin, int employeeId,int limit,int page, List<Integer> statusList) {
        return orderRepository.getOrderDetailByAdmin("%" + accountName + "%", "%" + employeeName + "%",
                 codeProduct, "%" + customerName + "%", customerPhoneNumber,
                LocalDate.parse(dateStart), LocalDate.parse(dateEnd),isAdmin,employeeId,limit,page,statusList);
    }

    @Override
    public LocalDate getMaxDateStart() {
        LocalDate dateMax = orderRepository.getMaxDateStart().toLocalDate();
        return dateMax;
    }

    @Override
    public LocalDate getMinDateEnd() {
        LocalDate dateMin = orderRepository.getMinDateStart().toLocalDate();
        return dateMin;
    }

    @Override
    public List<OrderDetail> getOrderDetailByProduct_IdOrderByDateStart(int id) {
        return orderRepository.getOrderDetailByProduct_IdOrderByDateStart(id);
    }

    @Override
    public int updateOrder(String dateStart, int quantityBook, int customerId, int employeeId, int productId, double price, int id) {
        return orderRepository.updateOrder(LocalDate.parse(dateStart),quantityBook,customerId,employeeId,productId,price,id);
    }


    @Override
    @Transactional
    public void importProduct(int productId, int quantity) {
         orderRepository.import_product(productId,quantity);
    }

    @Override
    public List<ICustomerNoOrderDTO> getListCustomerNoOrder(LocalDate dateStart, LocalDate dateEnd, int limit, int page) {
        return orderRepository.getListCustomerNoOrder(dateStart,dateEnd,limit,page);
    }

    @Override
    public int countRecordByOrderRequest(String accountName, String employeeName, String codeProduct, String customerName,
                                         String customerPhoneNumber, String dateStart, String dateEnd,
                                         boolean isAdmin, int employeeId) {
        return orderRepository.countRecordByOrderRequest(accountName, employeeName, codeProduct, customerName,
                customerPhoneNumber, LocalDate.parse(dateStart), LocalDate.parse(dateEnd), isAdmin, employeeId);
    }

    @Override
    public int saveOrder(String dateStart, int quantityBook, int customerId, int employeeId, int productId, int statusId, double price) {
        return orderRepository.saveOrder(LocalDate.parse(dateStart), quantityBook, customerId, employeeId, productId, statusId, price);
    }

    @Override
    public List<IProductAnalystDTO> getListProductBestSeller(LocalDate dateStart, LocalDate dateEnd, int limit, int page) {
        return orderRepository.getListProductBestSeller(dateStart,dateEnd,limit,page);
    }


    @Override
    public List<IProductAnalystDTO> getListProductNoSeller(LocalDate dateStart, LocalDate dateEnd, int limit, int page) {
        return orderRepository.getListProductNoBought(dateStart,dateEnd,limit,page);
    }

    @Override
    public double getTotalRecordByProductBestSeller(LocalDate dateStart, LocalDate dateEnd) {
        return orderRepository.getTotalRecordByProductBestSeller(dateStart,dateEnd);
    }

    @Override
    public double getTotalRecordByProductNoSeller(LocalDate dateStart, LocalDate dateEnd) {
        return orderRepository.getTotalRecordByProductNoSeller(dateStart,dateEnd);
    }


}
