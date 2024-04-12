package com.example.springmvc.service;

import com.example.springmvc.model.Customer;

import java.util.List;
import java.util.Map;

public interface ICustomerService {
	
	List<Map<String, Object>> getListCustomer (String customerName, String customerPhoneNumber, int limit, int offset);
	
	int countRecordOfCustomer(String customerName, String customerPhoneNumber);
	
	int deleteCustomer(int id);
	int editCustomer(String name, String address, int version,String phoneNumber, int id);

	Customer getCustomerById (int id);
	int checkPhoneNumberExists(String phoneNumber, int id);

}
