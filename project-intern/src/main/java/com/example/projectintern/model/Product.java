package com.example.projectintern.model;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code_product",nullable = false)
    private String codeProduct;
    @Column(name = "name_product",nullable = false)
    private String nameProduct;
    @Column(name = "price_sell",nullable = false)
    private Double priceSell;
    @Column(name = "price_buy",nullable = false)
    private Double priceBuy;
    @Column(columnDefinition = "bit(1) default 0",nullable = false)
    private boolean flag;

    @Column(columnDefinition = "int default 1",nullable = false)
    private int version;
    public Product(int id, String codeProduct, String nameProduct, Double priceSell, Double priceBuy, boolean flag,int version) {
        this.id = id;
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.priceSell = priceSell;
        this.priceBuy = priceBuy;
        this.flag = flag;
        this.version = version;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(Double priceSale) {
        this.priceSell = priceSale;
    }

    public Double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(Double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
