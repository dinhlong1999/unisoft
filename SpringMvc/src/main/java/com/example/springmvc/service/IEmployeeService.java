package com.example.springmvc.service;

import java.util.List;
import java.util.Map;

import com.example.springmvc.model.Employee;

public interface IEmployeeService {
	List<Map<String,Object>> getAllEmployee(String username, String employeeName,String phoneNumber,int limit, int offset);
	
	int countTotalRow(String username, String employeeName, String phoneNumber);
	
	Employee getEmployeeByAccountId(int accountId);
	
	int updateStatusEmployee(int status,int id, int version);

}
