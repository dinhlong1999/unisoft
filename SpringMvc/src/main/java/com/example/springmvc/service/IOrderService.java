package com.example.springmvc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IOrderService {
	
	List<Map<String, Object>> getListOrder(String username, String employeeName, String codeProduct, String nameProduct, String customerName,
			 String phoneNumber, boolean isAdmin, int employeeId, LocalDate dateStart, LocalDate dateEnd,int statusAllocation, int statusBooking, int limit, int offset);

	int getTotalRecordByOrder(String username, String employeeName, String codeProduct, String nameProduct, String customerName,
			 String phoneNumber, boolean isAdmin, int employeeId, LocalDate dateStart, LocalDate dateEnd, int statusAllocation, int statusBooking);

}
