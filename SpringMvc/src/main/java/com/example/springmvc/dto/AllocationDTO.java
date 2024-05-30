package com.example.springmvc.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.springmvc.model.Allocation;


public class AllocationDTO {
	private static final String ERROR_CODE = "Mã sản phẩm và tên sản phẩm không được để trống.";
	private static final String ERROR_QUANTITY = "Số lượng nhập hàng không hợp lệ.";
	private List<Allocation> allocationList;

	public AllocationDTO() {
		
	}
	public List<Allocation> getAllocationList() {
		return allocationList;
	}

	public void setAllocationList(List<Allocation> allocationList) {
		this.allocationList = allocationList;
	}

	public static List<String> validateAllocation(List<Allocation> allocations){
		List<String> error = new ArrayList<>();
		for (Allocation allocationTemp: allocations) {
			if (allocationTemp.getProductCode().isEmpty() || allocationTemp.getProductName().isEmpty()) {
				if (!error.contains(ERROR_CODE)) {
					error.add(ERROR_CODE);
				}
			}
			if (allocationTemp.getQuantity() != null) {
				if (allocationTemp.getQuantity() <= 0) {
					if (!error.contains(ERROR_QUANTITY)) {
						error.add(ERROR_QUANTITY);
					}
				}
			}else {
				if (!error.contains(ERROR_QUANTITY)) {
					error.add(ERROR_QUANTITY);
				}

			}
		}
		return  error;
	}

}
