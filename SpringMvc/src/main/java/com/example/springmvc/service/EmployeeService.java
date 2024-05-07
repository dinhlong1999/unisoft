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
	public List<Map<String, Object>> getAllEmployee(String accountName, String employeeName, String phone, int limit,
			int offset) {
		return employeeMapper.getAllEmployee(accountName, employeeName, phone, limit, offset);
	}

	@Override
	public int countTotalRow(String accountName, String employeeName, String phone) {
		return employeeMapper.countTotalRow(accountName, employeeName, phone);
	}

	@Override
	public Employee getEmployeeByAccountId(int accountId) {
		return employeeMapper.getEmployeeByAccountId(accountId);
	}

	@Override
	public int updateStatusEmployee(int status, int version, int employeeId ) {
		return employeeMapper.updateStatusEmployee(status, version, employeeId );
	}

	@Override
	public int deleteEmployeeById(int employeeId, int version) {
		return employeeMapper.deleteEmployeeById(employeeId, version);
	}

	@Override
	@Transactional
	public int insertEmployee(Account account, Employee employee) {
		int roleIdOfUser = 2;
		int rowEffectByInsertAccount = accountMapper.insertAccount(account.getUsername(), BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()),roleIdOfUser);
		if (rowEffectByInsertAccount == 1) {
			Map<String,Object> getIdByAccountAddNew = accountMapper.getAccountByUsername(account.getUsername());
			int accountId = (int) getIdByAccountAddNew.get("id");
			int rowEffectByInsertEmployee = employeeMapper.insertEmployee(employee.getName(),employee.getPhone(),accountId);
			if (rowEffectByInsertEmployee == 1) {
				return 1;
			}else {
				throw new RuntimeException("Không thể thêm được nhân viên");
			}
		}else {
			
		}
		throw new RuntimeException("Không thể thêm được tài khoản");
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		Employee employee = new Employee();
		Map<String, Object> emplMap = employeeMapper.getEmployeeById(employeeId);
		if(emplMap== null) {
			return null;
		}
		employee.setId((int) emplMap.get("id"));
		employee.setName((String) emplMap.get("name"));
		employee.setPhone((String) emplMap.get("phone"));
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
			int rowEffectByEditEmployee = employeeMapper.updateEmployee(employee.getPhone(), employee.getVersion(), employee.getName(),employee.getId());
			if (rowEffectByEditEmployee == 1) {
				return 1;
			}else {
				throw new RuntimeException("Không thể cập nhật nhân viên");
			}
		}else {
			throw new RuntimeException("Không thể cập nhật tài khoản");
		}
		
	}

	




}
