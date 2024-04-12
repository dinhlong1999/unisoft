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
	public List<Map<String, Object>> getListCustomer(String customerName, String customerPhoneNumber, int limit, int offset) {
		return customerMapper.getListCustomer(customerName, customerPhoneNumber, limit, offset) ;
	}

	@Override
	public int countRecordOfCustomer(String customerName, String customerPhoneNumber) {
		return customerMapper.countRecordOfCustomer(customerName, customerPhoneNumber);
	}

	@Override
	public int deleteCustomer(int id) {
		return customerMapper.deleteCustomer(id);
	}

	@Override
	public int editCustomer(String name, String address, int version,String phoneNumber, int id) {
		int rowEffectByEditCustomer = customerMapper.editCustomer(name,address,version,phoneNumber,id);
		if (rowEffectByEditCustomer == 1){
			return 1;
		}else {
			return 0;
		}

	}

	@Override
	public Customer getCustomerById(int id) {
		Map<String,Object> customerMap = customerMapper.getCustomerById(id);
		Customer customer = new Customer();
		Employee employee = new Employee();
		customer.setId((int) customerMap.get("id"));
		customer.setName((String) customerMap.get("name"));
		customer.setAddress((String) customerMap.get("address"));
		customer.setPhoneNumber((String) customerMap.get("phoneNumber"));
		customer.setVersion((int) customerMap.get("version"));
		employee.setId((int) customerMap.get("employeeId"));
		employee.setName((String) customerMap.get("employeeName"));
		customer.setEmployeeName(employee);
		return customer;
	}

	@Override
	public int checkPhoneNumberExists(String phoneNumber, int id) {
		return customerMapper.checkPhoneNumberExists(phoneNumber,id);
	}


}
