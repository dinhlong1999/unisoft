package com.example.springmvc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmvc.repository.AnalystMapper;



@Service
public class AnalystService implements IAnalystService {
	
	@Autowired
	private AnalystMapper analystMapper;

	@Override
	public List<Map<String, Object>> getCustomerNoOrders(LocalDate orderDayBegin, LocalDate orderDayEnd, int limit, int page) {
		// TODO Auto-generated method stub
		return analystMapper.getCustomerNoOrders(orderDayBegin, orderDayEnd, limit, page);
	}

	@Override
	public int getTotalRowByCustomerNoOrders(LocalDate orderDayBegin, LocalDate orderDayEnd) {
		// TODO Auto-generated method stub
		return analystMapper.getTotalRowByCustomerNoOrders(orderDayBegin, orderDayEnd);
	}

	@Override
	public List<Map<String, Object>> getProductBestSeller(LocalDate orderDayBegin, LocalDate orderDayEnd, int limit, int page) {
		// TODO Auto-generated method stub
		return analystMapper.getProductBestSeller(orderDayBegin, orderDayEnd, limit, page);
	}

	@Override
	public int getTotalRowByProductBestSeller(LocalDate orderDayBegin, LocalDate orderDayEnd) {
		// TODO Auto-generated method stub
		return analystMapper.getTotalRowByProductBestSeller(orderDayBegin, orderDayEnd);
	}

	@Override
	public List<Map<String, Object>> getProductNoSeller(LocalDate orderDayBegin, LocalDate orderDayEnd, int limit, int page) {
		// TODO Auto-generated method stub
		return analystMapper.getProductNoSeller(orderDayBegin, orderDayEnd, limit, page);
	}

	@Override
	public int getTotalRowByProductNoSeller(LocalDate orderDayBegin, LocalDate orderDayEnd) {
		// TODO Auto-generated method stub
		return analystMapper.getTotalRowByProductNoSeller(orderDayBegin, orderDayEnd);
	}

}
