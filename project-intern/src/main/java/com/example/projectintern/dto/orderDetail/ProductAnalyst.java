package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productAnalyst",propOrder = {
        "productId" , "codeProduct", "nameProduct","quantity"
})
public class ProductAnalyst {
    @XmlElement(required = true)
    private int productId;
    @XmlElement(required = true)
    private String codeProduct;
    @XmlElement(required = true)
    private String nameProduct;
    @XmlElement(required = true)
    private int quantity;

    public ProductAnalyst() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
