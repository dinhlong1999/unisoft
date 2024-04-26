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
	public List<Map<String, Object>> getCustomerNoOrders(LocalDate dateStart, LocalDate dateEnd, int limit, int page) {
		// TODO Auto-generated method stub
		return analystMapper.getCustomerNoOrders(dateStart, dateEnd, limit, page);
	}

	@Override
	public int getTotalRowByCustomerNoOrders(LocalDate dateStart, LocalDate dateEnd) {
		// TODO Auto-generated method stub
		return analystMapper.getTotalRowByCustomerNoOrders(dateStart, dateEnd);
	}

	@Override
	public List<Map<String, Object>> getProductBestSeller(LocalDate dateStart, LocalDate dateEnd, int limit, int page) {
		// TODO Auto-generated method stub
		return analystMapper.getProductBestSeller(dateStart, dateEnd, limit, page);
	}

	@Override
	public int getTotalRowByProductBestSeller(LocalDate dateStart, LocalDate dateEnd) {
		// TODO Auto-generated method stub
		return analystMapper.getTotalRowByProductBestSeller(dateStart, dateEnd);
	}

	@Override
	public List<Map<String, Object>> getProductNoSeller(LocalDate dateStart, LocalDate dateEnd, int limit, int page) {
		// TODO Auto-generated method stub
		return analystMapper.getProductNoSeller(dateStart, dateEnd, limit, page);
	}

	@Override
	public int getTotalRowByProductNoSeller(LocalDate dateStart, LocalDate dateEnd) {
		// TODO Auto-generated method stub
		return analystMapper.getTotalRowByProductNoSeller(dateStart, dateEnd);
	}

}
