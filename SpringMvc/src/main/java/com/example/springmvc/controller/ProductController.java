package com.example.springmvc.controller;

import com.example.springmvc.common.Paging;
import com.example.springmvc.dto.ProductDTO;
import com.example.springmvc.model.Account;
import com.example.springmvc.model.Product;
import com.example.springmvc.service.IAccountService;
import com.example.springmvc.service.IProductService;

import javax.validation.Valid;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/product")
public class ProductController {
	
    @Autowired
    private IProductService productService;
   
    @Autowired
    private IAccountService accountService;
    
    
    
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
                       @RequestParam(required = false,defaultValue = "") String productCode,
                       @RequestParam(required = false,defaultValue = "") String productName,
                       Model model,RedirectAttributes redirectAttributes) {
    	 Account accountLogin = getAccountLogin();
    	 try {
    		 if (page != 0){
    			 page = page -1;
    		 }
    		 if (page < 0 ) {
    			 return "redirect:/product/list";
    		 }
    		 int limit = 4;
        	 if (accountLogin.getRole().getName().equals("ROLE_ADMIN")) {
        		 model.addAttribute("isAdmin",true);
        	 }else {
        		 model.addAttribute("isAdmin",false);
        	 }
        	 List<Product> productList = productService.getListProduct(productCode,productName,limit,limit*page);
             int totalRow = productService.totalRowGetListProduct(productCode,productName) ;
             double temp = (double) totalRow / limit ;
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
             model.addAttribute("limit",limit);
             model.addAttribute("totalPage",totalPage);
             model.addAttribute("showStartEllipsis",showStartEllipsis);
             model.addAttribute("showEndEllipsis",showEndEllipsis);
             
             
             model.addAttribute("productList",productList);
             model.addAttribute("productCode",productCode);
             model.addAttribute("productName",productName);
             model.addAttribute("nameLogin",accountLogin.getUsername());
		} catch (Exception  e) {
			 System.out.println(e.getMessage());
			 model.addAttribute("error", "Lỗi !!! Vui lòng thử lại.");
			 model.addAttribute("productList", new ArrayList<>());
			 model.addAttribute("productCode",productCode);   
			 model.addAttribute("productName",productName);
			 model.addAttribute("nameLogin",accountLogin.getUsername());
		}
        return "product/show";
    }
    
    @PostMapping("/destroy")
    public String delete(@RequestParam int id,
    					 @RequestParam int version,
    					 @RequestParam int page,
    					 @RequestParam String productName,
    					 @RequestParam String productCode, 
    					 RedirectAttributes redirectAttributes) {
    	try {
    		 int rowEffect = 0;
    		 rowEffect = productService.deleteProductById(id,version);
    		 if (rowEffect == 1) {
    			 redirectAttributes.addFlashAttribute("success","Xóa thành công.");
    			 return "redirect:/product/list?page=" + page + "&productCode="+productCode + "&productName=" +productName;
    		 }else {
    			 redirectAttributes.addFlashAttribute("error","Xóa thất bại.");
    			 return "redirect:/product/list?page=" + page + "&productCode="+productCode + "&productName=" +productName;
    		 }
    	} catch (Throwable e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error","Lỗi !!! Vui lòng thử lại.");
			return "redirect:/product/list?page=" + page + "&productCode="+productCode + "&productName=" +productName;
		}
    }
    
    @GetMapping("/create")
    public String create(Model model) {
    	Account account = getAccountLogin();
    	model.addAttribute("productDTO",new ProductDTO());
    	model.addAttribute("nameLogin",account.getUsername());
    	model.addAttribute("isAdmin", account.getRole().getName().equals("ROLE_ADMIN"));
    	return "product/create";	
    }
    
    @PostMapping("/store")
    public String store(@Valid @ModelAttribute ("productDTO") ProductDTO productDTO,
    					@RequestParam int page,
    					@RequestParam String productCode,
    					@RequestParam String productName, 
    					BindingResult bindingResult,Errors errors, 
    					RedirectAttributes redirectAttributes, Model model) {
    	Account account = getAccountLogin();
    	try {
    		new ProductDTO().validate(productDTO,bindingResult);
    		if (!productService.isCodeProductExists(productDTO.getCode())){
    			errors.rejectValue("code", null, "Mã sản phẩm không được trùng");
    		}
    		if (!productService.isNameProductExists(productDTO.getName())){
    			errors.rejectValue("name", null, "Tên sản phẩm không được trùng");
    		}
    		if(bindingResult.hasFieldErrors()) {
    			model.addAttribute("productDTO",productDTO);
    			model.addAttribute("nameLogin",account.getUsername());
    			model.addAttribute("isAdmin", account.getRole().getName().equals("ROLE_ADMIN"));
    			return "product/create";
    		}
    		Product product = new Product();
    		BeanUtils.copyProperties(productDTO, product);
    		int result = productService.insertProduct(product);
    		if( result == 0 ) {
                redirectAttributes.addFlashAttribute("error","Thêm mới thất bại.");
        		return "redirect:/product/list?page=" + page + "&productCode="+productCode + "&productName=" +productName;
        	}else {
                redirectAttributes.addFlashAttribute("success","Thêm mới thành công.");
        		return "redirect:/product/list?page=" + page + "&productCode="+productCode + "&productName=" +productName;
        	}
		}catch (Throwable e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", "Lỗi !!! Vui lòng thử lại.");
			return "redirect:/product/list?page=" + page + "&productCode="+productCode + "&productName=" +productName;
		}
    	
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id,Model model,RedirectAttributes redirectAttributes) {
    	Account account = getAccountLogin();
    	try {
    		Product product = productService.getProductById(id);
    		if (product == null) {
				redirectAttributes.addFlashAttribute("error", "Không tồn tại sản phẩm này trong hệ thống");
				return "redirect:/product/list";
			}
        	ProductDTO productDTO = new ProductDTO();
        	BeanUtils.copyProperties(product, productDTO);
        	model.addAttribute("productDTO",productDTO);
        	model.addAttribute("nameLogin",account.getUsername());
        	model.addAttribute("isAdmin", account.getRole().getName().equals("ROLE_ADMIN"));
        	return "product/edit";
		} catch (Throwable e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", "Lỗi !!! Vui lòng thử lại.");
			return "redirect:/product/list";
		}
    	
    }
    
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("productDTO") ProductDTO productDTO,
    					 @RequestParam int page,
    				     @RequestParam String productName,
    					 @RequestParam String productCode , 
    				     BindingResult bindingResult,Errors errors, 
    					 RedirectAttributes redirectAttributes, Model model) {
    	Account account = getAccountLogin();
    	try {
    		if (!productService.isCodeProductExistsToUpdate(productDTO.getCode(),productDTO.getId())) {
    			errors.rejectValue("code", null,"Mã sản phẩm không được trùng");
    		}
    		if (!productService.isNameProductExistsToUpdate(productDTO.getName(), productDTO.getId())) {
    			errors.rejectValue("name", null,"Tên sản phẩm không được trùng");
    		}
    		new ProductDTO().validate(productDTO, bindingResult);
    		if (bindingResult.hasErrors()) {
    			model.addAttribute("nameLogin",account.getUsername());
    			model.addAttribute("isAdmin", account.getRole().getName().equals("ROLE_ADMIN"));
    			model.addAttribute("productDTO",productDTO);
    			return "product/edit";
    		}
    		Product product = new Product();
    		BeanUtils.copyProperties(productDTO, product);
    		int result = productService.updateProduct(product);
    		if (result != 1) {
    			redirectAttributes.addFlashAttribute("error","Chỉnh sửa thất bại.");
    			return "redirect:/product/list?page=" + page + "&productCode="+productCode + "&productName=" +productName;
    		}else {
    			redirectAttributes.addFlashAttribute("success","Chỉnh sửa thành công.");
    			return "redirect:/product/list?page=" + page + "&productCode="+productCode + "&productName=" +productName;    	
    		}
    	}catch (Throwable e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error","Lỗi !!! Vui lòng thử lại");
			return "redirect:/product/list?page=" + page + "&productCode="+productCode + "&productName=" +productName;
		}
    	
    }
}
