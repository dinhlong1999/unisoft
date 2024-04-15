package com.example.springmvc.controller;

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


import java.util.List;


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
    public String getListProduct(@RequestParam(required = false,defaultValue = "0") int page,
                                 @RequestParam(required = false,defaultValue = "") String codeProduct,
                                 @RequestParam(required = false,defaultValue = "") String nameProduct,
                                 Model model,RedirectAttributes redirectAttributes) {
        if (page != 0){
            page = page -1;
        }
        if (page < 0) {
        	return "redirect:/product/list";
		}
        int limit = 4;
        try {
        	 Account accountLogin = getAccountLogin();
        	 if (accountLogin.getRole().getName().equals("ROLE_ADMIN")) {
        		 model.addAttribute("isAdmin",true);
        	 }else {
        		 model.addAttribute("isAdmin",false);
        	 }
        	 List<Product> productList = productService.getListProduct(codeProduct,nameProduct,limit,limit*page);
             int totalRow = productService.totalRowGetListProduct(codeProduct,nameProduct) ;
             double temp = (double) totalRow / limit ;
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
             model.addAttribute("limit",limit);
             model.addAttribute("totalPage",totalPage);
             model.addAttribute("showStartEllipsis",showStartEllipsis);
             model.addAttribute("showEndEllipsis",showEndEllipsis);
             
             
             
             model.addAttribute("productList",productList);
             model.addAttribute("codeProduct",codeProduct);
             model.addAttribute("nameProduct",nameProduct);
             model.addAttribute("nameLogin",accountLogin.getUsername());
		} catch (Exception  e) {
		System.out.println(e.getMessage());	
		
		}
        return "product/listproduct";
    }
    
    @PostMapping("/delete")
    public String deleteProduct(@RequestParam int idDelete, RedirectAttributes redirectAttributes) {
    	int rowEffect = 0;
    	try {
    		 rowEffect = productService.deleteProductById(idDelete);
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
    	if (rowEffect == 1) {
    		redirectAttributes.addFlashAttribute("message","Xóa thành công.");
    		return "redirect:/product/list";
    	}else {
    		redirectAttributes.addFlashAttribute("message","Xóa thất bại.");
    		return "redirect:/product/list";
    	}
    }
    
    @GetMapping("/showform")
    public String showFormCreate(Model model) {
    	Account account = getAccountLogin();
    	model.addAttribute("productDTO",new ProductDTO());
    	model.addAttribute("nameLogin",account.getUsername());
    	return "product/formcreateproduct";	
    }
    
    @PostMapping("/create")
    public String saveProduct(@Valid @ModelAttribute ("productDTO") ProductDTO productDTO,@RequestParam int page,
    												 @RequestParam String nameSearch,@RequestParam String codeSearch , 
    												 BindingResult bindingResult,Errors errors, 
    												 RedirectAttributes redirectAttributes, Model model) {
        new ProductDTO().validate(productDTO,bindingResult);
        if (!productService.isCodeProductExists(productDTO.getCodeProduct())){
        	errors.rejectValue("codeProduct", null, "Mã sản phẩm không được trùng");

        }
        if (!productService.isNameProductExists(productDTO.getNameProduct())){
        	errors.rejectValue("nameProduct", null, "Tên sản phẩm không được trùng");
        }
    	if(bindingResult.hasFieldErrors()) {
    		model.addAttribute("productDTO",productDTO);
    		return "product/formcreateproduct";
    	}
    	Product product = new Product();
    	BeanUtils.copyProperties(productDTO, product);
    	int result = 0;
    	try {
    		 result = productService.insertProduct(product);
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
    	if(result == 0) {
            redirectAttributes.addFlashAttribute("message","Thêm mới thất bại.");
    		return "redirect:/product/list?page=" + page + "&codeProduct="+codeSearch + "&nameProduct=" +nameSearch;
    	}else {
            redirectAttributes.addFlashAttribute("message","Thêm mới thành công.");
    		return "redirect:/product/list?page=" + page + "&codeProduct="+codeSearch + "&nameProduct=" +nameSearch;
    	}
    }
    
    @GetMapping("/showformedit/{id}")
    public String showFromEdit(@PathVariable("id") int id,Model model,RedirectAttributes redirectAttributes) {
    	Account account = getAccountLogin();
    	try {
    		Product product = productService.getProductById(id);
    		if (product == null) {
				redirectAttributes.addFlashAttribute("message", "Không tồn tại sản phẩm này trong hệ thống");
				return "redirect:/product/list";
			}
        	ProductDTO productDTO = new ProductDTO();
        	BeanUtils.copyProperties(product, productDTO);
        	model.addAttribute("productDTO",productDTO);
        	model.addAttribute("nameLogin",account.getUsername());
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
    	
    	return "product/formupdateproduct";
    }
    
    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute("productDTO") ProductDTO productDTO,
    								@RequestParam int page,@RequestParam String nameSearch,
    								@RequestParam String codeSearch , BindingResult bindingResult,Errors errors, 
    								RedirectAttributes redirectAttributes, Model model) {
    	if (!productService.isCodeProductExistsToUpdate(productDTO.getCodeProduct(),productDTO.getId())) {
    		errors.rejectValue("codeProduct", null,"Mã sản phẩm không được trùng");
    	}
    	if (!productService.isNameProductExistsToUpdate(productDTO.getNameProduct(), productDTO.getId())) {
    		errors.rejectValue("nameProduct", null,"Tên sản phẩm không được trùng");
    	}
    	new ProductDTO().validate(productDTO, bindingResult);
    	if (bindingResult.hasErrors()) {
			model.addAttribute("productDTO",productDTO);
			return "product/formupdateproduct";
		}
    	Product product = new Product();
    	BeanUtils.copyProperties(productDTO, product);
    	int result = 0;
    	try {
    		 result = productService.updateProduct(product);
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
    	if (result != 1) {
    		redirectAttributes.addFlashAttribute("message","Chỉnh sửa thất bại.");
    		return "redirect:/product/list?page=" + page + "&codeProduct="+codeSearch + "&nameProduct=" +nameSearch;
		}else {
			redirectAttributes.addFlashAttribute("message","Chỉnh sửa thành công.");
	    	return "redirect:/product/list?page=" + page + "&codeProduct="+codeSearch + "&nameProduct=" +nameSearch;
	    	
		}
    	
    }
}
