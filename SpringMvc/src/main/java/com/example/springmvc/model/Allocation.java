package com.example.springmvc.model;

public class Allocation {
	
	
	private String codeProduct;
	
	private String nameProduct;
	
	private int quantity;

	public Allocation() {
		
	}
	

	public Allocation(String codeProduct, String nameProduct, int quantity) {
		super();
		this.codeProduct = codeProduct;
		this.nameProduct = nameProduct;
		this.quantity = quantity;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Allocation [codeProduct=" + codeProduct + ", nameProduct=" + nameProduct + ", quantity=" + quantity
				+ "]";
	}
	
	
	
	
	
	
}
