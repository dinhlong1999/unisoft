package com.example.springmvc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IOrderService {
	
	List<Map<String, Object>> getListOrder(String username, String employeeName, String codeProduct, String nameProduct, String customerName,
			 String phoneNumber, boolean isAdmin, int employeeId, LocalDate dateStart, LocalDate dateEnd,int statusAllocation, int statusBooking, int limit, int offset);

	int getTotalRecordByOrder(String username, String employeeName, String codeProduct, String nameProduct, String customerName,
			 String phoneNumber, boolean isAdmin, int employeeId, LocalDate dateStart, LocalDate dateEnd, int statusAllocation, int statusBooking);

	int insertOrders(int customerId, int employeeId, int productId, int statusId, LocalDateTime dateStart,double price, int quantityBook );
	
	
	int updateOrder(int customerId, int productId, int quantitybook, int version, int id);
}
