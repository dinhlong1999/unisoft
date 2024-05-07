package com.example.springmvc.service;

import java.util.List;
import java.util.Map;

import com.example.springmvc.model.Customer;
import com.example.springmvc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmvc.repository.CustomerMapper;

@Service
public class CustomerService implements ICustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public List<Map<String, Object>> getListCustomer(String customerName, String customerPhone, int limit, int offset) {
		return customerMapper.getListCustomer(customerName, customerPhone, limit, offset) ;
	}

	@Override
	public int countRecordOfCustomer(String customerName, String customerPhone) {
		return customerMapper.countRecordOfCustomer(customerName, customerPhone);
	}

	@Override
	public int deleteCustomer(int customerId, int version) {
		return customerMapper.deleteCustomer(customerId, version);
	}

	@Override
	public int editCustomer(String customerName, String customerAddress, int version,String customerPhone, int customerId) {
		int rowEffectByEditCustomer = customerMapper.editCustomer(customerName,customerAddress,version,customerPhone,customerId);
		if (rowEffectByEditCustomer == 1){
			return 1;
		}else {
			return 0;
		}

	}

	@Override
	public Customer getCustomerById(int customerId) {
		Map<String,Object> customerMap = customerMapper.getCustomerById(customerId);
		if(customerMap == null) {
			return null;
		}
		Customer customer = new Customer();
		Employee employee = new Employee();
		customer.setId((int) customerMap.get("id"));
		customer.setName((String) customerMap.get("name"));
		customer.setAddress((String) customerMap.get("address"));
		customer.setPhone((String) customerMap.get("phone"));
		customer.setVersion((int) customerMap.get("version"));
		employee.setId((int) customerMap.get("employeeId"));
		employee.setName((String) customerMap.get("employeeName"));
		customer.setEmployee(employee);
		return customer;
	}

	@Override
	public int checkPhoneNumberExists(String customerPhone, int customerId) {
		return customerMapper.checkPhoneNumberExists(customerPhone,customerId);
	}

	@Override
	public int saveCustomer(String customerName, String customerAddress, String customerPhone, int employeeId) {
		return customerMapper.saveCustomer(customerName, customerAddress, customerPhone, employeeId);
	}

	@Override
	public String getPhoneNumberByNameCustomer(String customerName) {
		return customerMapper.getPhoneNumberByNameCustomer(customerName);
	}

	@Override
	public String getNameByPhoneNumberCustomer(String customerPhone) {
		return customerMapper.getNameByPhoneNumberCustomer(customerPhone);
	}

	@Override
	public int getIdCustomerByPhoneNumber(String customerPhone) {
		return customerMapper.getIdCustomerByPhoneNumber(customerPhone);
	}


}
