package com.example.springmvc.dto;

import java.util.List;

import com.example.springmvc.model.Allocation;

public class AllocationDTO {
	
	private List<Allocation> allocationList;

	public AllocationDTO() {
		
	}

	public List<Allocation> getAllocationList() {
		return allocationList;
	}

	public void setAllocationList(List<Allocation> allocationList) {
		this.allocationList = allocationList;
	}
	
	
}
