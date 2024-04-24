package com.example.springmvc.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springmvc.dto.AllocationDTO;
import com.example.springmvc.model.Account;
import com.example.springmvc.model.Allocation;
import com.example.springmvc.model.Product;
import com.example.springmvc.service.IAccountService;
import com.example.springmvc.service.IOrderService;
import com.example.springmvc.service.IProductService;

@Controller
public class AllocationController {
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IProductService productService;
	
	private Account getAccountLogin () {
		   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   if (!(authentication instanceof AnonymousAuthenticationToken)) {
				String usernameLogin = authentication.getName();
				Account account  = accountService.getAccountByUsername(usernameLogin);
				return account;
			}
		    	return null;
		}

	@GetMapping("/allocation")
	public String allocationPurchase(Model model) {
		Account account = getAccountLogin();
		Allocation allocation = new Allocation();
		List<Allocation> allocationList = new ArrayList<>();
		allocationList.add(allocation);
		AllocationDTO allocationDTO = new AllocationDTO();
		allocationDTO.setAllocationList(allocationList);
		model.addAttribute("nameLogin",account.getUsername());
		model.addAttribute("allocationDTO", allocationDTO);
		return "allocation/allocationProduct";
	}
	
	@PostMapping("/allocation")
	public String allocation(@ModelAttribute AllocationDTO allocationDTO, Model model,RedirectAttributes redirectAttributes ) {
		List<String> error = new ArrayList<>();
		List<Allocation> allocations = allocationDTO.getAllocationList();
		Account account = getAccountLogin();
		for (int i = 0; i < allocations.size(); i++) {
			if (allocations.get(i).getCodeProduct().isEmpty() || allocations.get(i).getNameProduct().isEmpty()) {
				if (!error.contains("Mã sản phẩm và tên sản phẩm không được để trống")) {
					error.add("Mã sản phẩm và tên sản phẩm không được để trống");	
				}
				
			}
			
			if (allocations.get(i).getQuantity() <= 0) {
				if (!error.contains("Số lượng nhập hàng không được nhỏ hơn hoặc bằng 0")) {
					error.add("Số lượng nhập hàng không được nhỏ hơn hoặc bằng 0");
				}
			
			}
		}
		
		if (error.size() != 0) {
			model.addAttribute("message", error);
			model.addAttribute("nameLogin", account.getUsername());
			model.addAttribute("allocationDTO", allocationDTO);
			return "allocation/allocationProduct";
		}else {
			Map<String,String> statusMap = new TreeMap<>();
			for(Allocation allocation : allocationDTO.getAllocationList()) {
				Product product = productService.getProductByCodeProduct(allocation.getCodeProduct());
				String result = orderService.goodsAllocation(product.getId(), allocation.getQuantity());
				if (result.equals("ERROR")) {
					statusMap.put(allocation.getCodeProduct(), "ERROR");
				}else if (result.equals("TRUE")) {
					statusMap.put(allocation.getCodeProduct(), "SUCCESS");
				}else {
					statusMap.put(allocation.getCodeProduct(), "UPDATE");
				}
			}
			for(String map: statusMap.keySet()) {
				if (statusMap.get(map).equals("SUCCESS") || statusMap.get(map).equals("UPDATE")) {
					for(int i = 0; i < allocations.size(); i++ ) {
						if (allocations.get(i).getCodeProduct().equals(map)) {
							allocations.remove(i);
						}
					}
				}
			}
			model.addAttribute("nameLogin", account.getUsername());
			if (allocations.size() == 0) {
				List<String> statusList = new ArrayList<>();
				for(String map: statusMap.keySet()) {
					if(statusMap.get(map).equals("UPDATE")) {
						statusList.add("Sản phẩm có mã "+map + " đã cập nhật tồn kho do không đủ hàng để phân bổ " );
					}else {
						statusList.add("Sản phẩm có mã "+map + " đã phân bổ hàng thành công ");
					}
					
				}
				redirectAttributes.addFlashAttribute("statusList",statusList);
				return "redirect:/orders/list";
			}else {
				allocationDTO.setAllocationList(allocations);
				model.addAttribute("allocationDT0",allocationDTO );
				return "allocation/allocationProduct";
			}
		}
	
	}
}
