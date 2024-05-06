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

	private int quantityBook;
	
	private int version;

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

	public int getQuantityBook() {
		return quantityBook;
	}

	public void setQuantityBook(int quantityBook) {
		this.quantityBook = quantityBook;
	}
	

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "OrdersDTO [id=" + id + ", codeProduct=" + codeProduct + ", nameProduct=" + nameProduct
				+ ", nameCustomer=" + nameCustomer + ", phoneNumber=" + phoneNumber + ", quantityBook=" + quantityBook
				+ "]";
	}

//	public List<Map<String, List<String>>> validateSaveOrder(List<OrdersDTO> ordersDTO) {
//		List<Map<String, List<String>>> errorList = new ArrayList<>();
//		Map<String, List<String>> mapError = new TreeMap<>();
//		for (int i = 0; i < ordersDTO.size(); i++) {
//			List<String> fieldErrorList = new ArrayList<>();
//			if (ordersDTO.get(i).getCodeProduct().isEmpty()) {
//				fieldErrorList.add("codeProduct");
//				fieldErrorList.add("nameProduct");
//			}
//			if (ordersDTO.get(i).getQuantityBook() <= 0) {
//				fieldErrorList.add("quantityBook");
//			}
//			if (ordersDTO.get(i).getNameCustomer().isEmpty()) {
//				fieldErrorList.add("customerName");
//				fieldErrorList.add("phoneNumber");
//			}
//			if (!fieldErrorList.isEmpty()) {
//				mapError.put(ordersDTO.get(i).getId(), fieldErrorList);
//			}
//			if (!mapError.isEmpty()) {
//				errorList.add(mapError);
//			}
//		}
//		return errorList;
//	}

	public Map<String, List<String>> validate(List<OrdersDTO> ordersDTOS) {
		Map<String, List<String>> errorList = new TreeMap<>();
		for (int i = 0; i < ordersDTOS.size(); i++) {
			List<String> fieldErrorList = new ArrayList<>();
			if (ordersDTOS.get(i).getCodeProduct().isEmpty()) {
				fieldErrorList.add("codeProduct");
				fieldErrorList.add("nameProduct");
			}
			if (ordersDTOS.get(i).getQuantityBook() <= 0) {
				fieldErrorList.add("quantityBook");
			}
			if (ordersDTOS.get(i).getNameCustomer().isEmpty()) {
				fieldErrorList.add("customerName");
				fieldErrorList.add("phoneNumber");
			}
			if (!fieldErrorList.isEmpty()) {
				errorList.put(ordersDTOS.get(i).getId(), fieldErrorList);
			}

		}
		return  errorList;
	}
}