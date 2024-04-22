package com.example.springmvc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmvc.repository.OrderMapper;

@Service
public class OrderService implements IOrderService {
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public List<Map<String, Object>> getListOrder(String username, String employeeName, String codeProduct,
			String nameProduct, String customerName, String phoneNumber, boolean isAdmin, int employeeId,
			LocalDate dateStart, LocalDate dateEnd, int statusAllocation, int statusBooking, int limit, int offset) {
		// TODO Auto-generated method stub
		return orderMapper.getListOrder(username, employeeName, codeProduct, nameProduct, customerName, phoneNumber, isAdmin, employeeId, dateStart, dateEnd, statusAllocation, statusBooking, limit, offset);
	}

	@Override
	public int getTotalRecordByOrder(String username, String employeeName, String codeProduct, String nameProduct,
			String customerName, String phoneNumber, boolean isAdmin, int employeeId, LocalDate dateStart,
			LocalDate dateEnd, int statusAllocation, int statusBooking) {
		// TODO Auto-generated method stub
		return orderMapper.getTotalRecordByOrder(username, employeeName, codeProduct, nameProduct, customerName, phoneNumber, isAdmin, employeeId, dateStart, dateEnd, statusAllocation, statusBooking);
	}

	@Override
	public int insertOrders(int customerId, int employeeId, int productId, int statusId, LocalDateTime dateStart,
			double price, int quantityBook) {
		// TODO Auto-generated method stub
		return orderMapper.insertOrders(customerId, employeeId, productId, statusId, dateStart, price, quantityBook);
	}

	@Override
	public int updateOrder(int customerId, int productId, int quantitybook, int version, int id) {
		// TODO Auto-generated method stub
		return orderMapper.updateOrder(customerId, productId, quantitybook, version, id);
	}

	@Override
	public int getOrderVersionById(int id) {
		return orderMapper.getOrderVersionById(id);
	}


}
