package com.example.springmvc.service;

import java.util.List;
import java.util.Map;

import com.example.springmvc.model.Account;
import com.example.springmvc.model.Employee;

public interface IEmployeeService {
	List<Map<String,Object>> getAllEmployee(String accountName, String employeeName,String phone,int limit, int offset);
	
	int countTotalRow(String accountName, String employeeName, String phone);
	
	Employee getEmployeeByAccountId(int accountId);
	
	int updateStatusEmployee(int status,int version,int employeeId);

	int deleteEmployeeById (int employeeId, int version);
	
	int insertEmployee(Account account, Employee employee);
	
	Employee getEmployeeById (int employeeId);
	
	int updateEmployee(Employee employee);
	
	

}
