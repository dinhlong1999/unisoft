package com.example.springmvc.controller;

import com.example.springmvc.model.Product;
import com.example.springmvc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        List<Product> productList = productService.getListProduct(codeProduct,nameProduct,4,4*page);
        int totalRow = productService.totalRowGetListProduct(codeProduct,nameProduct) ;
        double temp = (double) totalRow / 4 ;
        int totalPage = (int) Math.ceil(temp);
        model.addAttribute("productList",productList);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("codeProduct",codeProduct);
        model.addAttribute("nameProduct",nameProduct);
        return "listproduct";
    }
}