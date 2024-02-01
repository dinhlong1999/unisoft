package com.example.demosoap.interfaces;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
            "products"
})
@XmlRootElement(name = "getAllProductResponse")
public class GetAllProductResponse {
    @XmlElement(name = "products",required = true)
    private List<ProductInfo> products;

    public List<ProductInfo> getProducts() {
        if (products ==null){
            products = new ArrayList<>();
        }
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }
}
