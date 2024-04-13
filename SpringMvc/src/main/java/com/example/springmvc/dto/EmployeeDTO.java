package com.example.springmvc.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.springmvc.model.Account;

public class EmployeeDTO implements Validator {
	
	private int id;
	
	private String name;
	
	private String phoneNumber;
	
	private Account account;
	
	private String confirmPassword;
	
	private boolean flag;
	
	private int version;
	
	private boolean isOnline;
	
	public EmployeeDTO() {
		
	}
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	public boolean isOnline() {
		return isOnline;
	}


	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		EmployeeDTO employeeDTO = (EmployeeDTO) target;
		if (employeeDTO.getAccount().getUsername().isEmpty()) {
			errors.rejectValue("account.username",null,"Tên đăng nhập không được để trống");
		}
		if (!employeeDTO.getAccount().getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
			errors.rejectValue("account.password",null,"Mật khẩu không hợp lệ, Mật khẩu phải có ít nhất một chữ in hoa, và một kí tự số. Mật khẩu phải từ 8 kí tự trở lên");
		}
		if (employeeDTO.getName().isEmpty()) {
			errors.rejectValue("name",null ,"Tên nhân viên không được để trống");
		}
		if (!employeeDTO.getPhoneNumber().matches("^0[\\d]{9}$")){
			errors.rejectValue("phoneNumber",null,"Số điện thoại không được để trống");
		}
		if(!employeeDTO.getConfirmPassword().equals(employeeDTO.getAccount().getPassword())) {
			errors.rejectValue("confirmPassword",null, "Xác nhận mật khẩu không trùng khớp.");
		}
		
	}
	
	
	

	
	
}
