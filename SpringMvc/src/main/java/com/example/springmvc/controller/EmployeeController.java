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

import com.example.springmvc.common.CheckLogin;
import com.example.springmvc.common.Paging;
import com.example.springmvc.dto.EmployeeDTO;
import com.example.springmvc.model.Employee;
import com.example.springmvc.service.AuthenticationService;
import com.example.springmvc.service.IAccountService;
import com.example.springmvc.service.IEmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping("/list")
	public String show(@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "") String accountName,
			@RequestParam(required = false, defaultValue = "") String employeeName,
			@RequestParam(required = false, defaultValue = "") String phone, 
			Model model, RedirectAttributes redirectAttributes
			) {
		String accountNameLogin = "";
		boolean isAdmin = false;
		try {
			if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
				return "redirect:/logout";
		    }
			accountNameLogin = authenticationService.getAccountLogin().getUsername();
			isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
			
			if (page != 0) {
				page = page - 1;
			}
			if (page < 0) {
				return "redirect:/employee/list";
			}
			int limit = 3;
			List<Map<String, Object>> listEmployee = employeeService.getAllEmployee(accountName, employeeName,
					phone, limit, limit * page);
			int totalRow = employeeService.countTotalRow(accountName, employeeName, phone);
			double temp = (double) totalRow / limit;
			int totalPage = (int) Math.ceil(temp);
			
			 //handle Phân trang
	        Map<String,Object> pagination = Paging.handlePaging(page, totalPage);
	        int startPage = (int) pagination.get("startPage");
	        int endPage = (int) pagination.get("endPage");
			boolean showStartEllipsis = (boolean) pagination.get("showStartEllipsis");
			boolean showEndEllipsis = (boolean) pagination.get("showEndEllipsis");
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("page", page);
			model.addAttribute("limit", limit);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("showStartEllipsis", showStartEllipsis);
			model.addAttribute("showEndEllipsis", showEndEllipsis);
			model.addAttribute("listEmployee", listEmployee);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("listEmployee", new ArrayList<>());
			model.addAttribute("error", "Hệ thống bị lỗi, vui lòng thử lại");
		}
		model.addAttribute("accountName", accountName);
		model.addAttribute("employeeName", employeeName);
		model.addAttribute("phone", phone);
		model.addAttribute("isAdmin",isAdmin);
		model.addAttribute("nameLogin", accountNameLogin);
		return "employee/show";
		
	}

	@PostMapping("/destroy")
	public String delete(@RequestParam int employeeId,
						 @RequestParam int employeeVersion,
						 @RequestParam int accountId,
						 @RequestParam int accountVersion,
						 @RequestParam int page,
						 @RequestParam String accountName,
						 @RequestParam String employeeName,
						 @RequestParam String phone,
						 RedirectAttributes redirectAttributes) {
		try {
			if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
				return "redirect:/logout";
		    }
			int rowEffect = employeeService.deleteEmployeeById(employeeId,employeeVersion,accountId, accountVersion);
			if (rowEffect == 1) {
				redirectAttributes.addFlashAttribute("success", "Xóa thành công");
			} else {
				redirectAttributes.addFlashAttribute("error", "Xóa thất bại ");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", "Lỗi, vui lòng cập nhật lại");
		}
		return "redirect:/employee/list?page=" + page + "&accountName=" + accountName + "&employeeName=" + employeeName
				+ "&phone=" + phone;
		
	}

	@GetMapping("/create")
	public String create(Model model) {
		if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
			return "redirect:/logout";
	    }
		String accountNameLogin = authenticationService.getAccountLogin().getUsername();
		boolean isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
		model.addAttribute("nameLogin", accountNameLogin);
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("employeeDTO", new EmployeeDTO());
		return "employee/create";
	}

	@PostMapping("/store")
	public String store(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO, 
						@RequestParam int page,
						@RequestParam String accountName, 
						@RequestParam String employeeName, 
						@RequestParam String phoneSearch,
						BindingResult bindingResult, Errors errors, 
						Model model, RedirectAttributes redirectAttributes
						) {
		try {	
			String accountNameLogin = authenticationService.getAccountLogin().getUsername();
			boolean isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
			if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
				return "redirect:/logout";
		    }
			int checkUsernameExists = accountService.checkUsernameOfAccount(employeeDTO.getAccount().getUsername());
			if (checkUsernameExists == 1) {
				errors.rejectValue("account.username", null, "Tên đăng nhập đã bị trùng");
			}
			new EmployeeDTO().validate(employeeDTO, bindingResult);
			if (bindingResult.hasErrors()) {
				model.addAttribute("nameLogin",accountNameLogin);
				model.addAttribute("isAdmin", isAdmin);
				model.addAttribute("employeeDTO", employeeDTO);
				return "employee/create";
			}
			Employee employee = new Employee();
			BeanUtils.copyProperties(employeeDTO, employee);
			employee.setAccount(employeeDTO.getAccount());
			int rowEffectByInsertEmployee = employeeService.insertEmployee(employeeDTO.getAccount(), employee);
			if (rowEffectByInsertEmployee == 1) {
				redirectAttributes.addFlashAttribute("success", "Thêm mới thành công");
			} else {
				redirectAttributes.addFlashAttribute("error", "Thêm mới thất bại");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error","Lỗi. Vui lòng cập nhật lại");
		}
		return "redirect:/employee/list?page=" + page + "&accountName=" + accountName + "&employeeName=" + employeeName
				+ "&phone=" + phoneSearch;
		
	}

	 @GetMapping("/edit/{id}")
	 public String edit(@PathVariable("id") int id, Model model,RedirectAttributes redirectAttributes) {
		 try {
			 if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
					return "redirect:/logout";
			 }
			 String accountNameLogin = authenticationService.getAccountLogin().getUsername();
			 boolean isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
			 Employee employee = employeeService.getEmployeeById(id);
			 if (employee == null) {
				redirectAttributes.addFlashAttribute("error","Không tồn tại nhân viên này.");
				return "redirect:/employee/list";
			}
			 EmployeeDTO employeeDTO = new EmployeeDTO();
			 BeanUtils.copyProperties(employee, employeeDTO);
			 employeeDTO.setAccount(employee.getAccount());
			 model.addAttribute("employeeDTO",employeeDTO);
			 model.addAttribute("nameLogin",accountNameLogin);
			 model.addAttribute("isAdmin", isAdmin);
			 return "employee/edit";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error","Lỗi. Vui lòng cập nhật lại");
			return "redirect:/employee/list";
		}
		
	 }
	 
	 @PostMapping("/update")
	 public String update(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
			 			  @RequestParam int page,
			 			  @RequestParam String accountName, 
			 			  @RequestParam String employeeName, 
			 			  @RequestParam String phoneSearch,
			 			  BindingResult bindingResult, Errors errors,
			 			  Model model, RedirectAttributes redirectAttributes
			 			  ) {
		 
		 try {
			 if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
					return "redirect:/logout";
			 }
			 String accountNameLogin = authenticationService.getAccountLogin().getUsername();
			 boolean isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
			 int checkExistsUsername = accountService.checkUsernameExists(employeeDTO.getAccount().getUsername(), employeeDTO.getAccount().getId());
			 if (checkExistsUsername != 0) {
				 errors.rejectValue("account.username", null, "Tên tài khoản đã bị trùng, vui lòng nhập lại");
			
			 }
			 new EmployeeDTO().validate(employeeDTO, bindingResult);
			 if (bindingResult.hasErrors()) {
				 model.addAttribute("employeeDTO", employeeDTO);
				 model.addAttribute("nameLogin",accountNameLogin);
				 model.addAttribute("isAdmin", isAdmin);
				 return "employee/edit";
			 }
		
			 Employee employee = new Employee();
			 BeanUtils.copyProperties(employeeDTO, employee);
			 employee.setAccount(employeeDTO.getAccount());
			 int rowEffectByEditEmployee = employeeService.updateEmployee(employee);
			 if (rowEffectByEditEmployee == 1) {
				redirectAttributes.addFlashAttribute("success", "Chỉnh sửa thành công");		
			 } else {
				redirectAttributes.addFlashAttribute("error", "Chỉnh sửa thất bại");
			 }
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", "Cập nhật thất bại. Vui lòng thử lại");	
		}
		return "redirect:/employee/list?page=" + page + "&accountName=" + accountName + "&employeeName=" + employeeName
					+ "&phone=" + phoneSearch;
	 }

}