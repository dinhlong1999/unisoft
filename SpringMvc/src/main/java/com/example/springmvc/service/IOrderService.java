package com.example.springmvc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.example.springmvc.dto.OrdersDTO;

public interface IOrderService {
	
	List<Map<String, Object>> getListOrder(String username, String employeeName, String codeProduct, String nameProduct, String customerName,
			 String phoneNumber, boolean isAdmin, int employeeId, LocalDate dateStart, LocalDate dateEnd,int statusAllocation, int statusBooking, int limit, int offset);

	int getTotalRecordByOrder(String username, String employeeName, String codeProduct, String nameProduct, String customerName,
			 String phoneNumber, boolean isAdmin, int employeeId, LocalDate dateStart, LocalDate dateEnd, int statusAllocation, int statusBooking);

	
	int insertAndUpdateOrders(int accountId, List<OrdersDTO> ordersDTOs);
	
	String goodsAllocation(int productId, int quantityBook);
}
