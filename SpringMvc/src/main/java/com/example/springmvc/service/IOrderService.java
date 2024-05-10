package com.example.springmvc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.springmvc.dto.OrdersDTO;

public interface IOrderService {
	
	List<Map<String, Object>> getListOrder(String accountName, String employeeName, String productCode, String productName, String customerName,
			 String customerPhone, boolean isAdmin, int employeeId, LocalDate orderDayBegin, LocalDate orderDayEnd,int statusAllocation, int statusBooking, int limit, int offset);

	int getTotalRecordByOrder(String accountName, String employeeName, String productCode, String productName, String customerName,
			 String customerPhone, boolean isAdmin, int employeeId, LocalDate orderDayBegin, LocalDate orderDayEnd, int statusAllocation, int statusBooking);

	
	int insertAndUpdateOrders(int accountId, List<OrdersDTO> ordersDTOs);
	
	String goodsAllocation(int productId, int quantity);
}
