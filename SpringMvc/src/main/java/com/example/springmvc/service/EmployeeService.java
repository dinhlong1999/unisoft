package com.example.springmvc.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.springmvc.model.Account;
import com.example.springmvc.model.Employee;
import com.example.springmvc.repository.AccountMapper;
import com.example.springmvc.repository.EmployeeMapper;

@Service
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public List<Map<String, Object>> getAllEmployee(String username, String employeeName, String phoneNumber, int limit,
			int offset) {
		return employeeMapper.getAllEmployee(username, employeeName, phoneNumber, limit, offset);
	}

	@Override
	public int countTotalRow(String username, String employeeName, String phoneNumber) {
		return employeeMapper.countTotalRow(username, employeeName, phoneNumber);
	}

	@Override
	public Employee getEmployeeByAccountId(int accountId) {
		return employeeMapper.getEmployeeByAccountId(accountId);
	}

	@Override
	public int updateStatusEmployee(int status, int version, int id ) {
		return employeeMapper.updateStatusEmployee(status, version, id );
	}

	@Override
	public int deleteEmployeeById(int id) {
		
		return employeeMapper.deleteEmployeeById(id);
	}

	@Override
	@Transactional
	public int insertEmployee(Account account, Employee employee) {
		int roleIdOfUser = 2;
		int rowEffectByInsertAccount = accountMapper.insertAccount(account.getUsername(), BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()),roleIdOfUser);
		if (rowEffectByInsertAccount == 1) {
			Map<String,Object> getIdByAccountAddNew = accountMapper.getAccountByUsername(account.getUsername());
			int accountId = (int) getIdByAccountAddNew.get("id");
			int rowEffectByInsertEmployee = employeeMapper.insertEmployee(employee.getName(),employee.getPhoneNumber(),accountId);
			if (rowEffectByInsertEmployee == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = new Employee();
		Map<String, Object> emplMap = employeeMapper.getEmployeeById(id);
		employee.setId((int) emplMap.get("id"));
		employee.setName((String) emplMap.get("name"));
		employee.setPhoneNumber((String) emplMap.get("phoneNumber"));
		Account account = new Account();
		account.setId((int) emplMap.get("accountId"));
		account.setUsername((String) emplMap.get("username"));
		account.setVersion((int) emplMap.get("accountVersion"));
		employee.setAccount(account);
		employee.setVersion((int) emplMap.get("version"));
		return employee;
	}

	@Override
	@Transactional
	public int updateEmployee(Employee employee) {
		int rowEffectByEditAccount = accountMapper.editAccount(employee.getAccount().getUsername(),BCrypt.hashpw(employee.getAccount().getPassword(), BCrypt.gensalt()), 
																employee.getAccount().getVersion(), employee.getAccount().getId());
		if (rowEffectByEditAccount == 1) {
			int rowEffectByEditEmployee = employeeMapper.updateEmployee(employee.getPhoneNumber(), employee.getVersion(), employee.getName(),employee.getId());
			if (rowEffectByEditEmployee == 1) {
				return 1;
			}
		}
		return 0;
	}




}
