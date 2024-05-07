package com.example.springmvc.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.springmvc.model.Employee;

@Mapper
public interface EmployeeMapper {

	List<Map<String,Object>> getAllEmployee(String accountName, String employeeName, String phone, int limit, int page);
	
	int countTotalRow(String accountName, String employeeName, String phone);
	
	Employee getEmployeeByAccountId(int accountId);
	
	int updateStatusEmployee(int status, int version,int employeeId);
	
	int deleteEmployeeById ( int employeeId,  int version);
	
	int insertEmployee(String employeeName, String phone, int accountId);
	
	Map<String, Object> getEmployeeById (int employeeId);
	
	int updateEmployee(String phone, int version, String employeeName, int employeeId);
}
