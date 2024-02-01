package com.example.projectintern.service;

import com.example.projectintern.dto.orderDetail.IOrderDetailDTO;
import com.example.projectintern.model.OrderDetail;
import com.example.projectintern.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<IOrderDetailDTO> getOrderDetailByAdmin(String accountName, String employeeName, String codeProduct, String customerName, String customerPhoneNumber, String dateStart, String dateEnd) {
        return orderRepository.getOrderDetailByAdmin("%" + accountName + "%", "%" + employeeName + "%",
                "%" + codeProduct + "%", "%" + customerName + "%", customerPhoneNumber + "%",
                LocalDate.parse(dateStart), LocalDate.parse(dateEnd));
    }

    @Override
    public List<IOrderDetailDTO> getOrderDetailByUser(int idEmployee, String codeProduct, String customerName, String customerPhoneNumber, String dateStart, String dateEnd) {
        return orderRepository.getOrderDetailByUser(idEmployee, codeProduct + "%","%" +
                customerName + "%",customerPhoneNumber + "%",LocalDate.parse(dateStart), LocalDate.parse(dateEnd));
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
}
