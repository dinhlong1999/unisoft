package com.example.springmvc.model;

public class Employee {

    private int id;

    private String name;

    private String phoneNumber;

    private int accountId;

    private boolean flag;

    private int version;
    
    private boolean isOnline;

    public Employee() {
    }


    public Employee(int id, String name, String phoneNumber, int accountId, boolean flag, int version, boolean isOnline) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.accountId = accountId;
        this.flag = flag;
        this.version = version;
        this.isOnline = isOnline;
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean getFlag() {
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
    
}
