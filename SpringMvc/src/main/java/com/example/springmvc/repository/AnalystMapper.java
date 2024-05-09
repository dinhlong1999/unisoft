package com.example.springmvc.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnalystMapper {
	
	List<Map<String,Object>> getCustomerNoOrders(LocalDate orderDayBegin, LocalDate orderDayEnd, int limit, int page);
	
	int getTotalRowByCustomerNoOrders(LocalDate orderDayBegin, LocalDate orderDayEnd);
	
	List<Map<String, Object>> getProductBestSeller(LocalDate orderDayBegin, LocalDate orderDayEnd, int limit, int page);
	
	int getTotalRowByProductBestSeller(LocalDate orderDayBegin, LocalDate orderDayEnd);
	
	List<Map<String, Object>> getProductNoSeller(LocalDate orderDayBegin, LocalDate orderDayEnd, int limit, int page);
	
	int getTotalRowByProductNoSeller(LocalDate orderDayBegin, LocalDate orderDayEnd);
}
