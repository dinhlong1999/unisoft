package com.example.springmvc.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrdersDTO {

	private String id;

	private String productCode;

	private String productName;

	private String customerName;

	private String customerPhone;

	private int quantity;
	
	private int version;

	public OrdersDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	

	@Override
	public String toString() {
		return "OrdersDTO [id=" + id + ", productCode=" + productCode + ", productName=" + productName
				+ ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", quantity=" + quantity
				+ ", version=" + version + ", getId()=" + getId() + ", getProductCode()=" + getProductCode()
				+ ", getProductName()=" + getProductName() + ", getCustomerName()=" + getCustomerName()
				+ ", getCustomerPhone()=" + getCustomerPhone() + ", getQuantity()=" + getQuantity() + ", getVersion()="
				+ getVersion() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public Map<String, List<String>> validate(List<OrdersDTO> ordersDTOS) {
		Map<String, List<String>> errorList = new TreeMap<>();
		for (int i = 0; i < ordersDTOS.size(); i++) {
			List<String> fieldErrorList = new ArrayList<>();
			if (ordersDTOS.get(i).getProductCode().isEmpty()) {
				fieldErrorList.add("productCode");
				fieldErrorList.add("productName");
			}
			if (ordersDTOS.get(i).getQuantity() <= 0) {
				fieldErrorList.add("quantity");
			}
			if (ordersDTOS.get(i).getCustomerName().isEmpty()) {
				fieldErrorList.add("customerName");
				fieldErrorList.add("customerPhone");
			}
			if (!fieldErrorList.isEmpty()) {
				errorList.put(ordersDTOS.get(i).getId(), fieldErrorList);
			}

		}
		return  errorList;
	}
}