package com.example.projectintern.service;

import com.example.projectintern.dto.orderDetail.IOrderDetailDTO;
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
                                                String dateEnd);
    List<IOrderDetailDTO> getOrderDetailByUser(int idEmployee, String codeProduct,
                                               String customerName, String customerPhoneNumber,
                                               String dateStart, String dateEnd);
    LocalDate getMaxDateStart();
    LocalDate getMinDateEnd();
    List<OrderDetail> getOrderDetailByProduct_IdOrderByDateStart(int id);
}
