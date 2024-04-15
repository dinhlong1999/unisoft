package com.example.springmvc.service;

import java.time.LocalDate;
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
			String nameProduct, String customerName, String phoneNumber, LocalDate dateStart, LocalDate dateEnd,
			int statusAllocation, int statusBooking, int limit, int offset) {
		// TODO Auto-generated method stub
		return orderMapper.getListOrder(username, employeeName, codeProduct, nameProduct, customerName, phoneNumber, dateStart, dateEnd, statusAllocation,statusBooking, limit, offset);
	}

	@Override
	public int getTotalRecordByOrder(String username, String employeeName, String codeProduct, String nameProduct,
			String customerName, String phoneNumber, LocalDate dateStart, LocalDate dateEnd, int statusAllocation, int statusBooking) {
		// TODO Auto-generated method stub
		return orderMapper.getTotalRecordByOrder(username, employeeName, codeProduct, nameProduct, customerName, phoneNumber, dateStart, dateEnd, statusAllocation,statusBooking);
	}

	

	

}
