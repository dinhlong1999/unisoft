package com.example.springmvc.controller;

import com.example.springmvc.model.Product;
import com.example.springmvc.service.IProductService;

import dto.ProductDTO;
import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public String getListProduct(@RequestParam(required = false,defaultValue = "0") int page,
                                 @RequestParam(required = false,defaultValue = "") String codeProduct,
                                 @RequestParam(required = false,defaultValue = "") String nameProduct,
                                 Model model) {
        if (page != 0){
            page = page -1;
        }
        int limit = 3;
        List<Product> productList = productService.getListProduct(codeProduct,nameProduct,limit,limit*page);
        int totalRow = productService.totalRowGetListProduct(codeProduct,nameProduct) ;
        double temp = (double) totalRow / limit ;
        int totalPage = (int) Math.ceil(temp);
        model.addAttribute("productList",productList);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("codeProduct",codeProduct);
        model.addAttribute("nameProduct",nameProduct);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        return "listproduct";
    }
    
    @PostMapping("/delete")
    public String deleteProduct(@RequestParam int idDelete, RedirectAttributes redirectAttributes) {
    	int rowEffect = productService.deleteProductById(idDelete);
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
    	model.addAttribute("productDTO",new ProductDTO());
    	return "formcreateproduct";	
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
    		return "formcreateproduct";
    	}
    	Product product = new Product();
    	BeanUtils.copyProperties(productDTO, product);
    	int result = productService.insertProduct(product);
    	if(result == 0) {
            redirectAttributes.addFlashAttribute("message","Thêm mới thất bại.");
    		return "redirect:/product/list?page=" + page + "&codeProduct="+codeSearch + "&nameProduct=" +nameSearch;
    	}else {
            redirectAttributes.addFlashAttribute("message","Thêm mới thành công.");
    		return "redirect:/product/list?page=" + page + "&codeProduct="+codeSearch + "&nameProduct=" +nameSearch;
    	}
    }
    
    @GetMapping("/showformedit/{id}")
    public String showFromCreate(@PathVariable("id") int id,Model model) {
    	Product product = productService.getProductById(id);
    	ProductDTO productDTO = new ProductDTO();
    	BeanUtils.copyProperties(product, productDTO);
    	model.addAttribute("productDTO",productDTO);
    	return "formupdateproduct";
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
			return "formupdateproduct";
		}
    	Product product = new Product();
    	BeanUtils.copyProperties(productDTO, product);
    	int result = productService.updateProduct(product);
    	if (result != 1) {
    		redirectAttributes.addFlashAttribute("message","Chỉnh sửa thất bại.");
    		return "redirect:/product/list?page=" + page + "&codeProduct="+codeSearch + "&nameProduct=" +nameSearch;
		}else {
			redirectAttributes.addFlashAttribute("message","Chỉnh sửa thành công.");
	    	return "redirect:/product/list?page=" + page + "&codeProduct="+codeSearch + "&nameProduct=" +nameSearch;
	    	
		}
    	
    }
}
