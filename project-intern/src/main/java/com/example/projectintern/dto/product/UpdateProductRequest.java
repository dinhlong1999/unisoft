package com.example.projectintern.dto.product;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "product"
})
@XmlRootElement(name = "updateProductRequest")
public class UpdateProductRequest {

    @XmlElement(required = true)
    @NotNull(message = "Không được để trống")
    private ProductInfo product;

    public UpdateProductRequest() {
    }

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }
}
