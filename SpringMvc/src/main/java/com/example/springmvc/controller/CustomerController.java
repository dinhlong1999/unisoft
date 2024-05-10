package com.example.springmvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springmvc.common.Paging;
import com.example.springmvc.dto.CustomerDTO;
import com.example.springmvc.model.Customer;
import com.example.springmvc.model.Employee;
import com.example.springmvc.service.AuthenticationService;
import com.example.springmvc.service.ICustomerService;
import com.example.springmvc.service.IEmployeeService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IEmployeeService employeeService;
   
	@Autowired
	private AuthenticationService authenticationService;
	
	
	@GetMapping("/list")
	public String show(@RequestParam(required = false,defaultValue = "0") int page,
					   @RequestParam(required = false,defaultValue = "") String customerName,
		               @RequestParam(required = false,defaultValue = "") String customerPhone,
					   Model model,RedirectAttributes redirectAttributes) {
		String accountNameLogin = authenticationService.getAccountLogin().getUsername();
		boolean isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
		int accountId = authenticationService.getAccountLogin().getId();
		try {
			if (page != 0) {
				page = page - 1;
			}
			if (page < 0) {
				return "redirect:/customer/list";
			}
			int limit = 4;
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
	        model.addAttribute("nameLogin",accountNameLogin);
	        model.addAttribute("isAdmin", isAdmin);
	        model.addAttribute("accountId", accountId);
		} catch (Exception e) {
			 System.out.println(e.getMessage());
			 model.addAttribute("customerList", new ArrayList<>());
			 model.addAttribute("error","Lỗi !! Vui lòng thử lại.");
		  
		}
		 return "customer/show";
		
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
			}else {
				redirectAttributes.addFlashAttribute("error", "Xóa khách hàng thất bại ");
			}
			return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", "Lỗi !!! Vui lòng thử lại");
			return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;
		}
		
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id,Model model,RedirectAttributes redirectAttributes){
		String accountNameLogin = authenticationService.getAccountLogin().getUsername();
		int accountId = authenticationService.getAccountLogin().getId();
		try {
			Customer customer = customerService.getCustomerById(id);
			if (customer == null) {
				redirectAttributes.addFlashAttribute("error","Không tồn tại đối tượng này");
				return "redirect:/customer/list";
			}
			Employee employee = employeeService.getEmployeeByAccountId(accountId);
			if (employee != null) {
				boolean isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
				if ((customer.getEmployee().getId() != employee.getId()) && !isAdmin){
					redirectAttributes.addFlashAttribute("error","Bạn không có quyền cập nhật đối tượng này");
					return "redirect:/customer/list";
				}else {
					CustomerDTO customerDTO = new CustomerDTO();
					BeanUtils.copyProperties(customer,customerDTO);
					model.addAttribute("customerDTO",customerDTO);
					model.addAttribute("nameLogin",accountNameLogin);
			        model.addAttribute("isAdmin", isAdmin);
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
		String accountNameLogin = authenticationService.getAccountLogin().getUsername();
		boolean isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
		try {
			new CustomerDTO().validate(customerDTO, bindingResult);
			int checkPhoneNumberExists = customerService.checkPhoneNumberExists(customerDTO.getPhone(), customerDTO.getId());
			if (checkPhoneNumberExists != 0) {
				errors.rejectValue("phone", null, "Số điện thoại đã bị trùng");
			}
			
			if (bindingResult.hasErrors()) {
				model.addAttribute("customerDTO",customerDTO);
				model.addAttribute("nameLogin",accountNameLogin);
				model.addAttribute("isAdmin", isAdmin);
				return "customer/edit";
			}
			Customer customer = new Customer();
			BeanUtils.copyProperties(customerDTO, customer);
			int rowEffectByEditCustomer = customerService.editCustomer(customer.getName(), customer.getAddress(), customer.getVersion(), customer.getPhone(), customer.getId());
			if (rowEffectByEditCustomer == 1) {
				redirectAttributes.addFlashAttribute("success", "Chỉnh sửa thành công.");
			}else {
				redirectAttributes.addFlashAttribute("error", "Chỉnh sửa thất bại.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", "Lỗi !!! Vui lòng thử lại");
		}
		return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;
		
	}

	@GetMapping("/create")
	public String create(Model model){
		String accountNameLogin = authenticationService.getAccountLogin().getUsername();
		boolean isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
		model.addAttribute("customerDTO",new CustomerDTO());
		model.addAttribute("nameLogin",accountNameLogin);
		model.addAttribute("isAdmin", isAdmin);
		return "customer/create";
	}

	@PostMapping("/store")
	public String store(@ModelAttribute CustomerDTO customerDTO,
						@RequestParam String customerName,
						@RequestParam int page,
						@RequestParam String customerPhone,
						BindingResult bindingResult,Errors errors,
						Model model,RedirectAttributes redirectAttributes){
		String accountNameLogin = authenticationService.getAccountLogin().getUsername();
		boolean isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
		int accountId = authenticationService.getAccountLogin().getId();
		try {
			int checkPhoneNumberExists = customerService.checkPhoneNumberExists(customerDTO.getPhone(),0);
			if (checkPhoneNumberExists != 0){
				errors.rejectValue("phone",null,"Số điện thoại đã tồn tại");
			}
			new CustomerDTO().validate(customerDTO,bindingResult);
			if (bindingResult.hasErrors()){
				model.addAttribute("customerDTO",customerDTO);
				model.addAttribute("nameLogin",accountNameLogin);
				model.addAttribute("isAdmin", isAdmin);
				return "customer/create";
			}
			Customer customer = new Customer();
			BeanUtils.copyProperties(customerDTO,customer);
			Employee employee = employeeService.getEmployeeByAccountId(accountId);
			int rowEffectByInsertCustomer = customerService.saveCustomer(customer.getName(),customer.getAddress(),customer.getPhone(),employee.getId());
			if (rowEffectByInsertCustomer == 1){
				redirectAttributes.addFlashAttribute("success", "Thêm mới thành công.");
			}else {
				redirectAttributes.addFlashAttribute("error", "Thêm mới thất bại.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", "Lỗi !!! .Vui lòng thử lại");
		}
		return "redirect:/customer/list?page=" + page + "&customerName=" + customerName + "&customerPhone=" + customerPhone;
	}
	
	

}
