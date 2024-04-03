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
    public String showformCreate(Model model) {
    	model.addAttribute("productDTO",new ProductDTO());
    	return "formcreateproduct";	
    }
    
    @PostMapping("/create")
    public String saveProduct(@Valid @ModelAttribute ("productDTO") ProductDTO productDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        new ProductDTO().validate(productDTO,bindingResult);
        if (!productService.isCodeProductExists(productDTO.getCodeProduct())){
            bindingResult.addError(new FieldError("productDTO","codeProduct","Mã sản phẩm không được trùng"));
        }
        if (!productService.isNameProductExists(productDTO.getNameProduct())){
            bindingResult.addError(new FieldError("productDTO","nameProduct","Tên sản phẩm không được trùng"));
        }
    	if(bindingResult.hasFieldErrors()) {
    		return "formcreateproduct";
    	}
    	Product product = new Product();
    	BeanUtils.copyProperties(productDTO, product);
    	int result = productService.insertProduct(product);
    	if(result == 0) {
            redirectAttributes.addFlashAttribute("message","Thêm mới thất bại.");
    		return "redirect:/product/list";
    	}else {
            redirectAttributes.addFlashAttribute("message","Thêm mới thành công.");
    		return "redirect:/product/list";
    	}
    }
    
}
