package com.example.springmvc.model;


public class Customer {

    private int id;

    private String name;

    private String phoneNumber;

    private String address;

    private boolean flag;

    private int version;

    private Employee employeeName;
    
	public Customer() {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Employee getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(Employee employeeName) {
		this.employeeName = employeeName;
	}

  
}