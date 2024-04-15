package com.example.springmvc.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
	
	List<Map<String, Object>> getListOrder(String username, String employeeName, String codeProduct, String nameProduct, String customerName,
									 String phoneNumber, LocalDate dateStart, LocalDate  dateEnd, int statusAllocation, int statusBooking, int limit, int offset);
	
	int getTotalRecordByOrder(String username, String employeeName, String codeProduct, String nameProduct, String customerName,
									 String phoneNumber, LocalDate dateStart, LocalDate dateEnd, int statusAllocation, int statusBooking);
	

}
