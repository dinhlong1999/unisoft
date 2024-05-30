package com.example.springmvc.service;

import com.example.springmvc.model.Customer;

import java.util.List;
import java.util.Map;

public interface ICustomerService {
	
	List<Map<String, Object>> getListCustomer (String customerName, String customerPhone, int limit, int offset);
	
	int countRecordOfCustomer(String customerName, String customerPhone);
	
	int deleteCustomer(int customerId, int version);
	
	int editCustomer(String customerName, String customerAddress, int version,String customerPhone, int customerId);

	Customer getCustomerById (int customerId);
	
	int checkPhoneNumberExists(String customerPhone, int customerId);
	
	int saveCustomer(String customerName, String customerAddress, String customerPhone,int employeeId);

	String getPhoneNumberByNameCustomer(String customerName);

	String getNameByPhoneNumberCustomer(String customerPhone);

	int getIdCustomerByPhoneNumber(String customerPhone);
}
