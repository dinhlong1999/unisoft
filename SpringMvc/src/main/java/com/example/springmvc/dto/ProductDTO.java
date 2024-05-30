package com.example.springmvc.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductDTO	implements  Validator {
	private int id;
	private String code;
	private String name;
	private Double priceSell;
	private Double priceBuy;
	private boolean flag;
	private Integer inventory;
	private int version;
	public ProductDTO() {
	}
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getPriceSell() {
		return priceSell;
	}


	public void setPriceSell(Double priceSell) {
		this.priceSell = priceSell;
	}


	public Double getPriceBuy() {
		return priceBuy;
	}


	public void setPriceBuy(Double priceBuy) {
		this.priceBuy = priceBuy;
	}


	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public Integer getInventory() {
		return inventory;
	}


	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}


	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ProductDTO productDTO = (ProductDTO) target;
		String nameProduct = productDTO.getName().trim();
		if (productDTO.getCode().isEmpty()) {
			errors.rejectValue("code", null, "Mã sản phẩm không được để trống");
		}else if (!productDTO.getCode().matches("^PR-\\d{4}$")) {
			errors.rejectValue("code", null, "Mã sản phẩm không đúng định dạng. Định dạng đúng: PR-XXXX(X: CHỮ SỐ) ");
		}
		if (nameProduct.isEmpty()) {
			errors.rejectValue("name", null, "Tên sản phẩm không được để trống");
		}
		if (productDTO.getPriceSell() != null) {
			if (productDTO.getPriceSell() <= 0) {
				errors.rejectValue("priceSell", null, "Giá bán không hợp lệ");
			}
		}else {
			errors.rejectValue("priceSell", null, "Giá bán không hợp lệ");
		}
		if (productDTO.getPriceBuy() != null) {
			if (productDTO.getPriceBuy() <= 0) {
				errors.rejectValue("priceBuy", null, "Giá mua vào không hợp lệ");
			}
		}else {
			errors.rejectValue("priceBuy", null, "Giá mua không hợp lệ");
		}
		if (productDTO.getInventory() != null) {
			if (productDTO.getInventory() < 0) {
				errors.rejectValue("inventory", null, "Số lượng tồn kho nhập vào không hợp lệ");
			}
		}else {
			errors.rejectValue("inventory", null, "Số lượng tồn kho nhập vào không hợp lệ");
		}
		
		
	}
}
