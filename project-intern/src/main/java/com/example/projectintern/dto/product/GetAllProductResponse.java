package com.example.projectintern.dto.product;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "products"
})
@XmlRootElement(name = "getAllProductResponse")
public class GetAllProductResponse {
    @XmlElement(required = true)
    private List<ProductInfo> products;

    public GetAllProductResponse() {
    }

    public List<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }
}
