package com.example.projectintern.service;

import com.example.projectintern.dto.orderDetail.*;
import com.example.projectintern.model.OrderDetail;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IOrderDetailService {
    OrderDetail saveOrderDetail(OrderDetail orderDetail);

    List<IOrderDetailDTO> getOrderDetailByAdmin(String accountName, String employeeName,
                                                String codeProduct, String customerName,
                                                String customerPhoneNumber,
                                                String dateStart,
                                                String dateEnd,
                                                boolean isAdmin,
                                                int employeeId,
                                                int limit,
                                                int page);
    LocalDate getMaxDateStart();
    LocalDate getMinDateEnd();
    List<OrderDetail> getOrderDetailByProduct_IdOrderByDateStart(int id);

    int updateOrder(String dateStart,int quantityBook, int customerId, int employeeId, int productId,double price, int id);

    void importProduct(int productId,int quantity);

    List<ICustomerNoOrderDTO> getListCustomerNoOrder(LocalDate dateStart, LocalDate dateEnd,int limit, int page);

    int countRecordByOrderRequest(String accountName,String employeeName,String codeProduct,
                                  String customerName,String customerPhoneNumber,String dateStart,
                                  String dateEnd,boolean isAdmin,int employeeId);

    int saveOrder(String dateStart,int quantityBook,int customerId,
                  int employeeId,int productId, int statusId, double price);
    List<IProductAnalystDTO> getListProductBestSeller(LocalDate dateStart, LocalDate dateEnd, int limit, int page);
    List<IProductAnalystDTO> getListProductNoSeller(LocalDate dateStart, LocalDate dateEnd, int limit, int page);
}
