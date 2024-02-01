package com.example.projectintern.dto.product;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "product"
})
@XmlRootElement(name = "saveProductRequest")
public class SaveProductRequest {
    @NotNull(message = "Product cannot be null")
    @Valid
    private ProductInfo product;

    public SaveProductRequest() {
    }

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }

}
