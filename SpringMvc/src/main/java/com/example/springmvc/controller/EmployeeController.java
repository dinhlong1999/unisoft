package com.example.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.example.springmvc.dto.EmployeeDTO;
import com.example.springmvc.model.Account;
import com.example.springmvc.model.Employee;
import com.example.springmvc.service.IAccountService;
import com.example.springmvc.service.IEmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IAccountService accountService;

	private Account getAccountLogin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String usernameLogin = authentication.getName();
			Account account = accountService.getAccountByUsername(usernameLogin);
			return account;
		}
		return null;
	}

	@GetMapping("/list")
	public String getAllEmployee(@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "") String username,
			@RequestParam(required = false, defaultValue = "") String employeeName,
			@RequestParam(required = false, defaultValue = "") String phoneNumberSearch, Model model, RedirectAttributes redirectAttributes) {
		if (page != 0) {
			page = page - 1;
		}
		if (page < 0) {
			return "redirect:/employee/list";
		}
		int limit = 3;
		Account account = getAccountLogin();
		List<Map<String, Object>> listEmployee = employeeService.getAllEmployee(username, employeeName,
				phoneNumberSearch, limit, limit * page);
		int totalRow = employeeService.countTotalRow(username, employeeName, phoneNumberSearch);
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
		model.addAttribute("username", username);
		model.addAttribute("employeeName", employeeName);
		model.addAttribute("phoneNumberSearch", phoneNumberSearch);
		model.addAttribute("isAdmin", account.getRole().getName().equals("ROLE_ADMIN"));
		model.addAttribute("nameLogin", account.getUsername());
		return "employee/listEmployee";
	}

	@PostMapping("/delete")
	public String deleteEmployee(@RequestParam int idDelete, 
								 @RequestParam int page,
								 @RequestParam String username,
								 @RequestParam String employeeName,
								 @RequestParam String phoneNumberSearch,
								 RedirectAttributes redirectAttributes) {
		int rowEffect = 0;
		try {
			rowEffect = employeeService.deleteEmployeeById(idDelete);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		if (rowEffect == 1) {
			redirectAttributes.addFlashAttribute("message", "Xóa thành công");
			return "redirect:/employee/list?page=" + page + "&username=" + username + "&employeeName=" + employeeName
					+ "&phoneNumberSearch=" + phoneNumberSearch;
		} else {
			redirectAttributes.addFlashAttribute("message", "Xóa thất bại ");
			return "redirect:/employee/list?page=" + page + "&username=" + username + "&employeeName=" + employeeName
					+ "&phoneNumberSearch=" + phoneNumberSearch;
		}
	}

	@GetMapping("/showform")
	public String showFormCreate(Model model) {
		Account account = getAccountLogin();
		model.addAttribute("nameLogin", account.getUsername());
		model.addAttribute("isAdmin", account.getRole().getName().equals("ROLE_ADMIN"));
		model.addAttribute("employeeDTO", new EmployeeDTO());
		return "employee/formcreateemployee";
	}

	@PostMapping("/create")
	public String insertEmployee(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO, @RequestParam int page,
			@RequestParam String username, @RequestParam String employeeName, @RequestParam String phoneNumberSearch,
			BindingResult bindingResult, Errors errors, Model model, RedirectAttributes redirectAttributes) {
		Account account = getAccountLogin();
		int checkUsernameExists = accountService.checkUsernameOfAccount(employeeDTO.getAccount().getUsername());
		if (checkUsernameExists == 1) {
			errors.rejectValue("account.username", null, "Tên đăng nhập đã bị trùng");
		}
		new EmployeeDTO().validate(employeeDTO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("nameLogin", account.getUsername());
			model.addAttribute("isAdmin", account.getRole().getName().equals("ROLE_ADMIN"));
			model.addAttribute("employeeDTO", employeeDTO);
			return "employee/formcreateemployee";
		}

		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);
		Account accountSave = employeeDTO.getAccount();
		accountSave.setUsername(accountSave.getUsername().trim());
		employee.setAccount(accountSave);
		employee.setName(employee.getName().trim());
		try {
			int rowEffectByInsertEmployee = employeeService.insertEmployee(employeeDTO.getAccount(), employee);
			if (rowEffectByInsertEmployee == 1) {
				redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
				return "redirect:/employee/list?page=" + page + "&username=" + username + "&employeeName=" + employeeName
						+ "&phoneNumberSearch=" + phoneNumberSearch;
			} else {
				redirectAttributes.addFlashAttribute("message", "Thêm mới thất bại");
				return "redirect:/employee/list?page=" + page + "&username=" + username + "&employeeName=" + employeeName
						+ "&phoneNumberSearch=" + phoneNumberSearch;
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Thêm mới thất bại");
			return "redirect:/employee/list?page=" + page + "&username=" + username + "&employeeName=" + employeeName
					+ "&phoneNumberSearch=" + phoneNumberSearch;
		}
		
	}

	 @GetMapping("/showformedit/{id}")
	 public String showfromEdit(@PathVariable("id") int id, Model model,RedirectAttributes redirectAttributes) {
		 Account account = getAccountLogin();
		 Employee employee = employeeService.getEmployeeById(id);
		 if (employee == null) {
			redirectAttributes.addFlashAttribute("message","Không tồn tại nhân viên này.");
			return "redirect:/employee/list";
		}
		 EmployeeDTO employeeDTO = new EmployeeDTO();
		 BeanUtils.copyProperties(employee, employeeDTO);
		 employeeDTO.setAccount(employee.getAccount());
		 model.addAttribute("employeeDTO",employeeDTO);
		 model.addAttribute("nameLogin",account.getUsername());
		 model.addAttribute("isAdmin", account.getRole().getName().equals("ROLE_ADMIN"));
		 return "employee/showformupdateempl";
	 }
	 
	 @PostMapping("/edit")
	 public String editEmployee(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
			 					@RequestParam int page, @RequestParam String username,
			 					@RequestParam String employeeName, @RequestParam String phoneNumberSearch,
			 					BindingResult bindingResult, Errors errors,Model model, RedirectAttributes redirectAttributes) {
		 int checkExistsUsername = accountService.checkUsernameExists(employeeDTO.getAccount().getUsername(), employeeDTO.getAccount().getId());
		 if (checkExistsUsername != 0) {
			errors.rejectValue("account.username", null, "Tên tài khoản đã bị trùng, vui lòng nhập lại");
			
		}
		 Account account = getAccountLogin();
		new EmployeeDTO().validate(employeeDTO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("employeeDTO", employeeDTO);
			model.addAttribute("nameLogin",account.getUsername());
			model.addAttribute("isAdmin", account.getRole().getName().equals("ROLE_ADMIN"));
			return "employee/showformupdateempl";
		}
		
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);
		Account accountUpdate = employeeDTO.getAccount();
		accountUpdate.setUsername(accountUpdate.getUsername().trim());
		employee.setAccount(employeeDTO.getAccount());
		employee.setName(employee.getName().trim());
		try {
			int rowEffectByEditEmployee = employeeService.updateEmployee(employee);
			if (rowEffectByEditEmployee == 1) {
				redirectAttributes.addFlashAttribute("message", "Chỉnh sửa thành công");
				return "redirect:/employee/list?page=" + page + "&username=" + username + "&employeeName=" + employeeName
						+ "&phoneNumberSearch=" + phoneNumberSearch;
			} else {
				redirectAttributes.addFlashAttribute("message", "Chỉnh sửa thất bại");
				return "redirect:/employee/list?page=" + page + "&username=" + username + "&employeeName=" + employeeName
						+ "&phoneNumberSearch=" + phoneNumberSearch;
			
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Chỉnh sửa thất bại");
			return "redirect:/employee/list?page=" + page + "&username=" + username + "&employeeName=" + employeeName
					+ "&phoneNumberSearch=" + phoneNumberSearch;
		
		}
		
		
	 }

}