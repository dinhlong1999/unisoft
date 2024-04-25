package com.example.springmvc.dto;

import java.util.List;

import com.example.springmvc.model.Customer;
import com.example.springmvc.model.Product;

public class AnalystDTO {
	private List<Customer> customerList;
	
	private List<Product> productList;
	
	private int totalPage;
	
	private int startPage;
	
	private int endPage;
	
	private int limit;
	
	private String dateStart;
	
	private String dateEnd;
	
	private boolean showStartEllipsis;
	
	private boolean showEndEllipsis;
	
	public AnalystDTO() {
		
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isShowStartEllipsis() {
		return showStartEllipsis;
	}

	public void setShowStartEllipsis(boolean showStartEllipsis) {
		this.showStartEllipsis = showStartEllipsis;
	}

	public boolean isShowEndEllipsis() {
		return showEndEllipsis;
	}

	public void setShowEndEllipsis(boolean showEndEllipsis) {
		this.showEndEllipsis = showEndEllipsis;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	

	
	
	
	
	
}
