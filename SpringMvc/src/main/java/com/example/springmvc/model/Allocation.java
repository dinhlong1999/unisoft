package com.example.springmvc.model;

public class Allocation {
	private String productCode;
	
	private String productName;
	
	private Integer quantity;

	public Allocation() {
		
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Allocation{" +
				"productCode='" + productCode + '\'' +
				", productName='" + productName + '\'' +
				", quantity=" + quantity +
				'}';
	}
}
