package com.example.springmvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.springmvc.common.Paging;
import com.example.springmvc.dto.CustomerDTO;
import com.example.springmvc.model.Customer;
import com.example.springmvc.model.Employee;
import com.example.springmvc.service.IEmployeeService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springmvc.model.Account;
import com.example.springmvc.service.IAccountService;
import com.example.springmvc.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IEmployeeService employeeService;
   
	
	private Account getAccountLogin () {
	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	   if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String usernameLogin = authentication.getName();
			Account account  = accountService.getAccountByUsername(usernameLogin);
			return account;
		}
	    	return null;
	    }
	
	@GetMapping("/list")
	public String show(@RequestParam(required = false,defaultValue = "0") int page,
					   @RequestParam(required = false,defaultValue = "") String customerName,
		               @RequestParam(required = false,defaultValue = "") String customerPhone,
					   Model model,RedirectAttributes redirectAttributes) {
		Account account = getAccountLogin();
		try {
			if (page != 0) {
				page = page - 1;
			}
			if (page < 0) {
				return "redirect:/customer/list";
			}
			int limit = 4;
			
			if(account.getRole().getName().equals("ROLE_ADMIN")) {
				model.addAttribute("isAdmin", true);
			}else {
				model.addAttribute("accountId", account.getId());
			}
			List<Map<String, Object>> customerList = customerService.getListCustomer(customerName, customerPhone, limit, limit * page);
			int totalRecordOfCustomer = customerService.countRecordOfCustomer(customerName, customerPhone);
			double temp = (double) totalRecordOfCustomer / limit;
			int totalPage = (int) Math.ceil(temp);
			
			 //handle Phân trang
	        Map<String,Object> pagination = Paging.handlePaging(page, totalPage);
	        int startPage = (int) pagination.get("startPage");
	        int endPage = (int) pagination.get("endPage");
			boolean showStartEllipsis = (boolean) pagination.get("showStartEllipsis");
			boolean showEndEllipsis = (boolean) pagination.get("showEndEllipsis");
			
	        model.addAttribute("startPage",startPage);
	        model.addAttribute("endPage", endPage);
	        model.addAttribute("page",page);
	        model.addAttribute("limit", limit);
	        model.addAttribute("totalPage",totalPage);
	        model.addAttribute("showStartEllipsis",showStartEllipsis);
	        model.addAttribute("showEndEllipsis",showEndEllipsis);
	        
	        model.addAttribute("customerList", customerList);
	        model.addAttribute("customerName",customerName);
	        model.addAttribute("customerPhone", customerPhone);
	        model.addAttribute("nameLogin", account.getUsername());
	        model.addAttribute("isAdmin", account.getRole().getName().equals("ROLE_ADMIN"));
	        return "customer/show";
		} catch (Exception e) {
			 System.out.println(e.getMessage());
			 model.addAttribute("totalPage",0);
			 model.addAttribute("customerList", new ArrayList<>());
			 model.addAttribute("nameLogin", account.getUsername());
			 model.addAttribute("error","Lỗi !! Vui lòng thử lại.");
		     model.addAttribute("isAdmin", account.getRole().getName().equals("ROLE_ADMIN"));
		     return "customer/show";
		}
		
	}
	
	@PostMapping("/destroy")
	public String delete(@RequestParam int customerId,
						 @RequestParam int version,
						 @RequestParam int page,
						 @RequestParam String customerName,
						 @RequestParam String customerPhone,
						 RedirectAttributes redirectAttributes) {
		
		try {
			int rowEffectByDeleteCustomer = customerService.deleteCustomer(customerId, version);
			if (rowEffectByDeleteCustomer == 1) {
				redirectAttributes.addFlashAttribute("success", "Xóa khách hàng thành công");
				return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;
			}else {
				redirectAttributes.addFlashAttribute("error", "Xóa khách hàng thất bại ");
				return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", "Lỗi !!! Vui lòng thử lại");
			return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;
		}
		
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id,Model model,RedirectAttributes redirectAttributes){
		Account accountLogin = getAccountLogin();
		try {
			Customer customer = customerService.getCustomerById(id);
			if (customer == null) {
				redirectAttributes.addFlashAttribute("error","Không tồn tại đối tượng này");
				return "redirect:/customer/list";
			}
			Employee employee = employeeService.getEmployeeByAccountId(accountLogin.getId());
			if (employee != null) {
				if ((customer.getEmployee().getId() != employee.getId()) && !accountLogin.getRole().getName().equals("ROLE_ADMIN")){
					redirectAttributes.addFlashAttribute("error","Bạn không có quyền cập nhật đối tượng này");
					return "redirect:/customer/list";
				}else {
					CustomerDTO customerDTO = new CustomerDTO();
					BeanUtils.copyProperties(customer,customerDTO);
					model.addAttribute("customerDTO",customerDTO);
					model.addAttribute("nameLogin",accountLogin.getUsername());
					model.addAttribute("isAdmin", accountLogin.getRole().getName().equals("ROLE_ADMIN"));
				}
			}
			return "customer/edit";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", "Lỗi !!! Vui lòng thử lại");
			return"redirect:/customer/list";		
		}
		
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute CustomerDTO customerDTO,
						 @RequestParam int page,
						 @RequestParam String customerName,
						 @RequestParam String customerPhone,
						 BindingResult bindingResult,Errors errors, 
						 Model model, RedirectAttributes redirectAttributes) {
		Account accountLogin = getAccountLogin();
		try {
			new CustomerDTO().validate(customerDTO, bindingResult);
			int checkPhoneNumberExists = customerService.checkPhoneNumberExists(customerDTO.getPhone(), customerDTO.getId());
			if (checkPhoneNumberExists != 0) {
				errors.rejectValue("phone", null, "Số điện thoại đã bị trùng");
			}
			
			if (bindingResult.hasErrors()) {
				model.addAttribute("customerDTO",customerDTO);
				model.addAttribute("nameLogin",accountLogin.getUsername());
				model.addAttribute("isAdmin", accountLogin.getRole().getName().equals("ROLE_ADMIN"));
				return "customer/edit";
			}
			Customer customer = new Customer();
			BeanUtils.copyProperties(customerDTO, customer);
			int rowEffectByEditCustomer = customerService.editCustomer(customer.getName(), customer.getAddress(), customer.getVersion(), customer.getPhone(), customer.getId());
			if (rowEffectByEditCustomer == 1) {
				model.addAttribute("customerName", customerName);
				model.addAttribute("customerPhone", customerPhone);
				redirectAttributes.addFlashAttribute("success", "Chỉnh sửa thành công.");
				return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;
			}else {
				model.addAttribute("customerName", customerName);
				model.addAttribute("customerPhone", customerPhone);
				redirectAttributes.addFlashAttribute("error", "Chỉnh sửa thất bại.");
				return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;
			
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", "Lỗi !!! Vui lòng thử lại");
			return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;
		
		}
		
	}

	@GetMapping("/create")
	public String create(Model model){
		Account accountLogin = getAccountLogin();
		model.addAttribute("customerDTO",new CustomerDTO());
		model.addAttribute("nameLogin",accountLogin.getUsername());
		model.addAttribute("isAdmin", accountLogin.getRole().getName().equals("ROLE_ADMIN"));
		return "customer/create";
	}

	@PostMapping("/store")
	public String store(@ModelAttribute CustomerDTO customerDTO,
						@RequestParam String customerName,
						@RequestParam int page,
						@RequestParam String customerPhone,
						BindingResult bindingResult,Errors errors,
						Model model,RedirectAttributes redirectAttributes){
		Account accountLogin = getAccountLogin();
		try {
			int checkPhoneNumberExists = customerService.checkPhoneNumberExists(customerDTO.getPhone(),0);
			if (checkPhoneNumberExists != 0){
				errors.rejectValue("phone",null,"Số điện thoại đã tồn tại");
			}
			new CustomerDTO().validate(customerDTO,bindingResult);
			if (bindingResult.hasErrors()){
				model.addAttribute("customerDTO",customerDTO);
				model.addAttribute("nameLogin",accountLogin.getUsername());
				model.addAttribute("isAdmin", accountLogin.getRole().getName().equals("ROLE_ADMIN"));
				return "customer/create";
			}
			Customer customer = new Customer();
			BeanUtils.copyProperties(customerDTO,customer);
			Employee employee = employeeService.getEmployeeByAccountId(accountLogin.getId());
			int rowEffectByInsertCustomer = customerService.saveCustomer(customer.getName(),customer.getAddress(),customer.getPhone(),employee.getId());
			if (rowEffectByInsertCustomer == 1){
				model.addAttribute("customerName", customerName);
				model.addAttribute("customerPhone", customerPhone);
				redirectAttributes.addFlashAttribute("success", "Thêm mới thành công.");
				return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;
			}else {
				model.addAttribute("customerName", customerName);
				model.addAttribute("customerPhone", customerPhone);
				redirectAttributes.addFlashAttribute("error", "Thêm mới thất bại.");
				return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", "Lỗi !!! .Vui lòng thử lại");
			return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;
		
		}
		
	}
	
	

}
