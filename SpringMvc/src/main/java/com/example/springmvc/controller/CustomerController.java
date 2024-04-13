package com.example.springmvc.controller;

import java.util.List;
import java.util.Map;

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
	public String showListCustomer(@RequestParam(required = false,defaultValue = "0") int page,
								   @RequestParam(required = false,defaultValue = "") String customerName,
								   @RequestParam(required = false,defaultValue = "") String customerPhoneNumber,
								   Model model,RedirectAttributes redirectAttributes) {
		if (page != 0) {
			page = page - 1;
		}
		if (page < 0) {
			return "redirect:/customer/list";
		}
		int limit = 4;
		Account account = getAccountLogin();
		if(account.getRole().getName().equals("ROLE_ADMIN")) {
			model.addAttribute("isAdmin", true);
		}else {
			model.addAttribute("accountId", account.getId());
		}
		List<Map<String, Object>> customerList = customerService.getListCustomer(customerName, customerPhoneNumber, limit, limit * page);
		int totalRecordOfCustomer = customerService.countRecordOfCustomer(customerName, customerPhoneNumber);
		double temp = (double) totalRecordOfCustomer / limit;
		int totalPage = (int) Math.ceil(temp);
		//logic phân trang
		int maxVisitablePages = 10; //Số trang tối đa hiển thị
        int adjacentPages = 2;  //số trang bên cạnh trang hiện tại
        int startPage;
        int endPage;
        boolean showStartEllipsis = false; // Dấu ... đầu
        boolean showEndEllipsis = false;  // Dấu ... cuối 
        if (totalPage <= maxVisitablePages) {
    	    startPage = 1;
    	    endPage = totalPage;
    	} else {
    	    if (page <= maxVisitablePages - adjacentPages) {
    	        startPage = 1;
    	        endPage = maxVisitablePages;
    	        showEndEllipsis = true;
    	    } else if (page >= totalPage - adjacentPages) {
    	        startPage = totalPage - maxVisitablePages + 1;
    	        endPage = totalPage;
    	        showStartEllipsis = true;
    	    } else {
    	        startPage = page - adjacentPages;
    	        endPage = page + adjacentPages;
    	        showStartEllipsis = true;
    	        showEndEllipsis = true;
    	    }
    	}
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page",page);
        model.addAttribute("limit", limit);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("showStartEllipsis",showStartEllipsis);
        model.addAttribute("showEndEllipsis",showEndEllipsis);
        
        model.addAttribute("customerList", customerList);
        model.addAttribute("customerName",customerName);
        model.addAttribute("customerPhoneNumber", customerPhoneNumber);
        model.addAttribute("nameLogin", account.getUsername());
        return "customer/listCustomer";
	}
	
	@PostMapping("/delete")
	public String deleteCustomer(@RequestParam int idDelete,RedirectAttributes redirectAttributes) {
		int rowEffectByDeleteCustomer = customerService.deleteCustomer(idDelete);
		if (rowEffectByDeleteCustomer == 1) {
			redirectAttributes.addFlashAttribute("message", "Xóa khách hàng thành công");
			return "redirect:/customer/list";
		}else {
			redirectAttributes.addFlashAttribute("message", "Xóa khách hàng thất bại ");
			return "redirect:/customer/list";
		}
	}

	@GetMapping("/showformeditcustomer/{idUpdate}")
	public String showFormEdit(@PathVariable("idUpdate") int idUpdate,Model model,RedirectAttributes redirectAttributes){
		Account accountLogin = getAccountLogin();
		Customer customer = customerService.getCustomerById(idUpdate);
		if (customer == null) {
			redirectAttributes.addFlashAttribute("message","Không tồn tại đối tượng này");
			return "redirect:/customer/list";
		}
		Employee employee = employeeService.getEmployeeByAccountId(accountLogin.getId());
		if (employee != null) {
			if ((customer.getEmployeeName().getId() != employee.getId()) && !accountLogin.getRole().getName().equals("ROLE_ADMIN")){
				redirectAttributes.addFlashAttribute("message","Bạn không có quyền cập nhật đối tượng này");
				return "redirect:/customer/list";
			}else {
				CustomerDTO customerDTO = new CustomerDTO();
				BeanUtils.copyProperties(customer,customerDTO);
				model.addAttribute("customerDTO",customerDTO);
				model.addAttribute("nameLogin",accountLogin.getUsername());
			}
		}
		return "customer/showformeditcustomer";
	}
	
	@PostMapping("/edit")
	public String editCustomer(@ModelAttribute CustomerDTO customerDTO,
							   @RequestParam int page, @RequestParam String nameCustomer,
							   @RequestParam String phoneNumberCustomer,
							   BindingResult bindingResult,Errors errors, Model model, RedirectAttributes redirectAttributes) {
		new CustomerDTO().validate(customerDTO, bindingResult);
		int checkPhoneNumberExists = customerService.checkPhoneNumberExists(customerDTO.getPhoneNumber(), customerDTO.getId());
		if (checkPhoneNumberExists != 0) {
			errors.rejectValue("phoneNumber", null, "Số điện thoại đã bị trùng");
		}
		Account accountLogin = getAccountLogin();
		if (bindingResult.hasErrors()) {
			model.addAttribute("customerDTO",customerDTO);
			model.addAttribute("nameLogin",accountLogin.getUsername());
			return "customer/showformeditcustomer";
		}
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);
		int rowEffectByEditCustomer = customerService.editCustomer(customer.getName(), customer.getAddress(), customer.getVersion(), customer.getPhoneNumber(), customer.getId());
		if (rowEffectByEditCustomer == 1) {
			model.addAttribute("customerName", nameCustomer);
			model.addAttribute("customerPhoneNumber", phoneNumberCustomer);
			redirectAttributes.addFlashAttribute("message", "Chỉnh sửa thành công.");
			return "redirect:/customer/list?page=" + page + "&customerName=" + nameCustomer + "&customerPhoneNumber=" + phoneNumberCustomer;
		}else {
			model.addAttribute("customerName", nameCustomer);
			model.addAttribute("customerPhoneNumber", phoneNumberCustomer);
			redirectAttributes.addFlashAttribute("message", "Chỉnh sửa thất bại.");
			return "redirect:/customer/list?page=" + page + "&customerName=" + nameCustomer + "&customerPhoneNumber=" + phoneNumberCustomer;
		
		}
	}

	@GetMapping("/showformcreatecustomer")
	public String showFormCreateCustomer(Model model){
		Account accountLogin = getAccountLogin();
		model.addAttribute("customerDTO",new CustomerDTO());
		model.addAttribute("nameLogin",accountLogin.getUsername());
		return "customer/showformcreatecustomer";
	}

	@PostMapping("/create")
	public String createCustomer(@ModelAttribute CustomerDTO customerDTO,
								 @RequestParam String nameCustomer, @RequestParam int page,
								 @RequestParam String phoneNumberCustomer,BindingResult bindingResult,
								 Errors errors,Model model,RedirectAttributes redirectAttributes){

		Account accountLogin = getAccountLogin();
		int checkPhoneNumberExists = customerService.checkPhoneNumberExists(customerDTO.getPhoneNumber(),0);
		if (checkPhoneNumberExists != 0){
			errors.rejectValue("phoneNumber",null,"Số điện thoại đã tồn tại");
		}
		new CustomerDTO().validate(customerDTO,bindingResult);
		if (bindingResult.hasErrors()){
			model.addAttribute("customerDTO",customerDTO);
			model.addAttribute("nameLogin",accountLogin.getUsername());
			return "customer/showformcreatecustomer";
		}
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO,customer);
		Employee employee = employeeService.getEmployeeByAccountId(accountLogin.getId());
		int rowEffectByInsertCustomer = customerService.saveCustomer(customer.getName(),customer.getAddress(),customer.getPhoneNumber(),employee.getId());
		if (rowEffectByInsertCustomer == 1){
			model.addAttribute("customerName", nameCustomer);
			model.addAttribute("customerPhoneNumber", phoneNumberCustomer);
			redirectAttributes.addFlashAttribute("message", "Thêm mới thành công.");
			return "redirect:/customer/list?page=" + page + "&customerName=" + nameCustomer + "&customerPhoneNumber=" + phoneNumberCustomer;
		}else {
			model.addAttribute("customerName", nameCustomer);
			model.addAttribute("customerPhoneNumber", phoneNumberCustomer);
			redirectAttributes.addFlashAttribute("message", "Thêm mới thất bại.");
			return "redirect:/customer/list?page=" + page + "&customerName=" + nameCustomer + "&customerPhoneNumber=" + phoneNumberCustomer;

		}
	}
	
	

}
