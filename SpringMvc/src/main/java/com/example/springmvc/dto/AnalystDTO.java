package com.example.springmvc.dto;

import java.util.List;
import java.util.Map;

import com.example.springmvc.model.Customer;
import com.example.springmvc.model.Product;

public class AnalystDTO {
	
	private List<Map<String,Object>> customerList;
	
	private List<Map<String,Object>> productList;
	
	private int totalPage;
	
	private int startPage;
	
	private int endPage;
	
	private int limit;
	
	private String dateStart;
	
	private String dateEnd;
	
	private boolean showStartEllipsis;
	
	private boolean showEndEllipsis;
	
	private int page;
	
	public AnalystDTO() {
		
	}

	public List<Map<String, Object>> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Map<String, Object>> customerList) {
		this.customerList = customerList;
	}

	public List<Map<String, Object>> getProductList() {
		return productList;
	}

	public void setProductList(List<Map<String, Object>> productList) {
		this.productList = productList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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
