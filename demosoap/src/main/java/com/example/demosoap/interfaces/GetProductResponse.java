package com.example.demosoap.interfaces;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "productInfo"
})
@XmlRootElement(name = "getProductResponse")
public class GetProductResponse {
    @XmlElement(required = true)
    protected ProductInfo productInfo;

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
}
