package com.example.springmvc.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
	
	List<Map<String, Object>> getListCustomer (String customerName, String customerPhoneNumber, int limit, int offset);
	
	int countRecordOfCustomer(String customerName, String customerPhoneNumber);
	
	int deleteCustomer(int id);
	
	int editCustomer(String name, String address, int version,String phoneNumber, int id);
	
	Map<String,Object> getCustomerById (int id);
	
	int checkPhoneNumberExists(String phoneNumber, int id);

	int saveCustomer(String name, String address, String phoneNumber,int employeeId);

	String getPhoneNumberByNameCustomer(String nameCustomer);

	String getNameByPhoneNumberCustomer(String phoneNumber);
}
