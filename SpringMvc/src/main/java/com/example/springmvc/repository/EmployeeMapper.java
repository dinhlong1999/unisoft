package com.example.springmvc.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.springmvc.model.Employee;

@Mapper
public interface EmployeeMapper {

	List<Map<String,Object>> getAllEmployee(String username, String employeeName, String phoneNumber, int limit, int page);
	
	int countTotalRow(String username, String employeeName, String phoneNumber);
	
	Employee getEmployeeByAccountId(int accountId);
	
	int updateStatusEmployee(int status, int version,int id);
	
	int deleteEmployeeById (int id);
	
	int insertEmployee(String name, String phoneNumber, int accountId);
	
	Map<String, Object> getEmployeeById (int id);
	
	int updateEmployee(String phoneNumber, int version, String name, int id);
}
