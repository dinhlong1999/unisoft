package com.example.springmvc.dto;

import java.util.List;
import java.util.Map;

public class AnalystDTO {
	
	private List<Map<String,Object>> customerList;
	
	private List<Map<String,Object>> productList;
	
	private int totalPage;
	
	private int startPage;
	
	private int endPage;
	
	private int limit;
	
	private String orderDayBegin;
	
	private String orderDayEnd;
	
	private boolean showStartEllipsis;
	
	private boolean showEndEllipsis;
	
	private int page;
	
	private String messageError;
	
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

	public String getOrderDayBegin() {
		return orderDayBegin;
	}

	public void setOrderDayBegin(String orderDayBegin) {
		this.orderDayBegin = orderDayBegin;
	}

	public String getOrderDayEnd() {
		return orderDayEnd;
	}

	public void setOrderDayEnd(String orderDayEnd) {
		this.orderDayEnd = orderDayEnd;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
	
	

	
	
	
	
	
}
