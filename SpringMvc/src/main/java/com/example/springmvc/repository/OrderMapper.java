package com.example.springmvc.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
	
	List<Map<String, Object>> getListOrder(String accountName, String employeeName, String productCode, String productName, String customerName,
									 String customerPhone, boolean isAdmin, int employeeId, LocalDate orderDayBegin, LocalDate orderDayEnd, int statusAllocation, int statusBooking, int limit, int offset);
	
	int getTotalRecordByOrder(String accountName, String employeeName, String productCode, String productName, String customerName,
									 String customerPhone,  boolean isAdmin, int employeeId, LocalDate orderDayBegin, LocalDate orderDayEnd, int statusAllocation, int statusBooking);
	

	int insertOrders(int customerId, int employeeId, int productId, int statusId, LocalDateTime orderDay,double price, int quantity );
	
	
	int updateOrder(int customerId, int productId, int quantity, int version, double price ,int id);

	
	int getOrderVersionById(int id);
	
	
	String goodsAllocation(int productId, int quantityBook);
}
