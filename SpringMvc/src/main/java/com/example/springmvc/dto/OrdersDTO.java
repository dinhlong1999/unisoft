package com.example.springmvc.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrdersDTO {
	
	private String id;
	
	private String codeProduct;
	
	private String nameProduct;
	
	private String nameCustomer;
	
	private String phoneNumber;
	
	private String quantityBook;

	public OrdersDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeProduct() {
		return codeProduct;
	}

	public void setCodeProduct(String codeProduct) {
		this.codeProduct = codeProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getQuantityBook() {
		return quantityBook;
	}

	public void setQuantityBook(String quantityBook) {
		this.quantityBook = quantityBook;
	}

	@Override
	public String toString() {
		return "OrdersDTO [id=" + id + ", codeProduct=" + codeProduct + ", nameProduct=" + nameProduct
				+ ", nameCustomer=" + nameCustomer + ", phoneNumber=" + phoneNumber + ", quantityBook=" + quantityBook
				+ "]";
	}
	
	public List<Map<String,Map<String, String>>> validateSaveOrder(List<OrdersDTO> ordersDTO){
		 List<Map<String,Map<String, String>>> errorList = new ArrayList<>();
		 Map<String,String> errorFieldMap = new TreeMap<>();
			Map<String,String> errorObjectMap = new TreeMap<>();
		for (int i = 0; i < ordersDTO.size(); i++) {
			
			
			if(ordersDTO.get(i).getNameProduct().isEmpty()) {
				
			}
		}
		return errorList;
	}
	
}
