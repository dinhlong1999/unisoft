package com.example.springmvc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IAnalystService {
	
List<Map<String,Object>> getCustomerNoOrders(LocalDate dateStart, LocalDate dateEnd, int limit, int page);
	
	int getTotalRowByCustomerNoOrders(LocalDate dateStart, LocalDate dateEnd);
	
	List<Map<String, Object>> getProductBestSeller(LocalDate dateStart, LocalDate dateEnd, int limit, int page);
	
	int getTotalRowByProductBestSeller(LocalDate dateStart, LocalDate dateEnd);
	
	List<Map<String, Object>> getProductNoSeller(LocalDate dateStart, LocalDate dateEnd, int limit, int page);
	
	int getTotalRowByProductNoSeller(LocalDate dateStart, LocalDate dateEnd);

}
