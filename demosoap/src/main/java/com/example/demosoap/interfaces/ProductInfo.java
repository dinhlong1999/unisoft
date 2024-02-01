package com.example.demosoap.interfaces;

import com.example.demosoap.model.TypeProduct;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productInfo",propOrder = {
        "id",
        "name",
        "price",
        "typeProduct"
})
public class ProductInfo {
    @XmlElement(required = true)
    protected int id;

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected double price;

    @XmlElement(required = true)
    protected TypeProductInfo typeProduct;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TypeProductInfo getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProductInfo typeProduct) {
        this.typeProduct = typeProduct;
    }
}
