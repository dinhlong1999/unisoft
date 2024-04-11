package dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductDTO	implements  Validator {

	
	private int id;
	
	private String codeProduct;


	private String nameProduct;
	
	private double priceSell;
	
	private double priceBuy;
	
	private boolean flag;

	private int inventory;
	
	private int version;

	
	public ProductDTO() {
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
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


	public Double getPriceSell() {
		return priceSell;
	}


	public void setPriceSell(double priceSell) {
		this.priceSell = priceSell;
	}


	public Double getPriceBuy() {
		return priceBuy;
	}


	public void setPriceBuy(double priceBuy) {
		this.priceBuy = priceBuy;
	}


	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public int getInventory() {
		return inventory;
	}


	public void setInventory(int inventory) {
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
		if (productDTO.getCodeProduct().isEmpty()) {
			errors.rejectValue("codeProduct", null, "Mã sản phẩm không được để trống");
		}else if (!productDTO.getCodeProduct().matches("^PR-\\d{4}$")) {
			errors.rejectValue("codeProduct", null, "Mã sản phẩm không đúng định dạng. Định dạng đúng: PR-XXXX(X: CHỮ SỐ) ");
		}
		if (productDTO.getNameProduct().isEmpty()) {
			errors.rejectValue("nameProduct", null, "Tên sản phẩm không được để trống");
		}
		if (productDTO.getPriceBuy() <= 0) {
			errors.rejectValue("priceBuy", null, "Giá mua vào không hợp lệ");
		}
		if (productDTO.getPriceSell() <= 0) {
			errors.rejectValue("priceSell", null, "Giá bán ra không hợp lệ");
		}
		if (productDTO.getInventory() < 0) {
			errors.rejectValue("inventory", null, "Số lượng tồn kho nhập vào không hợp lệ");
		}
	}
}
