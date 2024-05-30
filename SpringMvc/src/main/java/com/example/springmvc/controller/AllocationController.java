package com.example.springmvc.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springmvc.common.CheckLogin;
import com.example.springmvc.dto.AllocationDTO;
import com.example.springmvc.model.Allocation;
import com.example.springmvc.model.Product;
import com.example.springmvc.service.AuthenticationService;
import com.example.springmvc.service.IOrderService;
import com.example.springmvc.service.IProductService;

@Controller
public class AllocationController {
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private AuthenticationService authenticationService;
	

	
	@GetMapping("/allocation")
	public String allocationPurchase(Model model) {
		String accountNameLogin="";
		boolean isAdmin=false;
		try {
			if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
				return "redirect:/logout";
		    }
			accountNameLogin = authenticationService.getAccountLogin().getUsername();
			isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Allocation allocation = new Allocation();
		List<Allocation> allocationList = new ArrayList<>();
		allocationList.add(allocation);
		AllocationDTO allocationDTO = new AllocationDTO();
		allocationDTO.setAllocationList(allocationList);
		model.addAttribute("nameLogin",accountNameLogin);
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("allocationDTO", allocationDTO);
		return "allocation/allocationProduct";
	}
	
	@PostMapping("/allocation")
	public String allocation(@ModelAttribute AllocationDTO allocationDTO, Model model,RedirectAttributes redirectAttributes ) {
		String accountNameLogin = "";
		boolean isAdmin = false;
		try {
			if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
				return "redirect:/logout";
		    }
			accountNameLogin = authenticationService.getAccountLogin().getUsername();
			isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
			List<Allocation> allocations = allocationDTO.getAllocationList(); 
			List<String> error = AllocationDTO.validateAllocation(allocations);
			if (!error.isEmpty()) {
				model.addAttribute("error", error.toString());
				model.addAttribute("nameLogin",accountNameLogin);
				model.addAttribute("isAdmin", isAdmin);
				model.addAttribute("allocationDTO", allocationDTO);
				return "allocation/allocationProduct";
			}else {
				Map<String,Integer> allocationsMap = new TreeMap<>();
				for (Allocation allocation : allocations){
					if (!allocationsMap.containsKey(allocation.getProductCode())) {
						allocationsMap.put(allocation.getProductCode(), allocation.getQuantity());
					}else {
						allocationsMap.put(allocation.getProductCode(), allocationsMap.get(allocation.getProductCode()) + allocation.getQuantity());
					}
				}
				Map<String,String> statusMap = new TreeMap<>();
				for(String key : allocationsMap.keySet()) {
					Product product = productService.getProductByCodeProduct(key);
					String result = orderService.goodsAllocation(product.getId(), allocationsMap.get(key));
					if (result.equals("ERROR")) {
						statusMap.put(key, "ERROR");
					}else if (result.equals("TRUE")) {
						statusMap.put(key, "SUCCESS");
					}else {
						statusMap.put(key, "UPDATE");
					}
				}
				for(String map: statusMap.keySet()) {
					if (statusMap.get(map).equals("SUCCESS") || statusMap.get(map).equals("UPDATE")) {
						for(int i = 0; i < allocations.size(); i++ ) {
							if (allocations.get(i).getProductCode().equals(map)) {
								allocations.remove(i);
								i--;
							}
						}
					}
				}
				model.addAttribute("nameLogin",accountNameLogin);
				model.addAttribute("isAdmin", isAdmin);
			 	if (allocations.isEmpty()) {
					StringBuilder success = new StringBuilder();
					for(String map: statusMap.keySet()) {
						if(statusMap.get(map).equals("UPDATE")) {
							success.append("\nMã sản phẩm: ").append(map).append(" đã phân bổ và cập nhật tồn kho thành công.");
						}else {
							success.append("\nMã sản phẩm: ").append(map).append(" đã phân bổ hàng thành công.");
						}
					}

					redirectAttributes.addFlashAttribute("success",success);
					return "redirect:/orders/list";
				}else {
					allocationDTO.setAllocationList(allocations);
					StringBuilder errorStatus = new StringBuilder();
					for(Allocation allocation: allocations) {
						errorStatus.append("\nKhông thể phân bổ cho mã sản phẩm: ").append(allocation.getProductCode().toUpperCase());
					}
					model.addAttribute("allocationDT0",allocationDTO );
					model.addAttribute("error",errorStatus);
					return "allocation/allocationProduct";
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "Lỗi !!! Vui lòng thử lại.");
			return "redirect:/allocation";
		}
	}
}
