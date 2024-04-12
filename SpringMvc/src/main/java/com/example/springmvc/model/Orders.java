package com.example.springmvc.model;


import java.time.LocalDateTime;

public class Orders {

    private int id;

    private Employee employee;

    private Product product;

    private Customer customer;

    private Status status;

    private int quantityBook;

    private LocalDateTime dateStart;

    private LocalDateTime dateAllocation;
    private double price;

    private int version;
    private boolean flag;


    public Orders() {
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public int getQuantityBook() {
		return quantityBook;
	}


	public void setQuantityBook(int quantityBook) {
		this.quantityBook = quantityBook;
	}


	public LocalDateTime getDateStart() {
		return dateStart;
	}


	public void setDateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}


	public LocalDateTime getDateAllocation() {
		return dateAllocation;
	}


	public void setDateAllocation(LocalDateTime dateAllocation) {
		this.dateAllocation = dateAllocation;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}

    
}