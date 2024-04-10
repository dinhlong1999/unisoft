package com.example.springmvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmvc.model.Employee;
import com.example.springmvc.repository.EmployeeMapper;

@Service
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public List<Map<String, Object>> getAllEmployee(String username, String employeeName, String phoneNumber, int limit,
			int offset) {
		return employeeMapper.getAllEmployee(username, employeeName, phoneNumber, limit, offset);
	}

	@Override
	public int countTotalRow(String username, String employeeName, String phoneNumber) {
		// TODO Auto-generated method stub
		return employeeMapper.countTotalRow(username, employeeName, phoneNumber);
	}

	@Override
	public Employee getEmployeeByAccountId(int accountId) {
		// TODO Auto-generated method stub
		return employeeMapper.getEmployeeByAccountId(accountId);
	}

	@Override
	public int updateStatusEmployee(int status, int version, int id ) {
		// TODO Auto-generated method stub
		return employeeMapper.updateStatusEmployee(status, version, id );
	}

	@Override
	public int deleteEmployeeById(int id) {
		// TODO Auto-generated method stub
		return employeeMapper.deleteEmployeeById(id);
	}

	
	
}
