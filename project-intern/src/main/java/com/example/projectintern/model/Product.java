package com.example.projectintern.model;

import javax.persistence.*;
import java.util.Objects;

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
    @Column(columnDefinition = "int default 0",nullable = false)
    private int inventory;

    @Column(columnDefinition = "int default 1",nullable = false)
    private int version;
    public Product(int id, String codeProduct, String nameProduct, Double priceSell, Double priceBuy, boolean flag,int version,int inventory) {
        this.id = id;
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.priceSell = priceSell;
        this.priceBuy = priceBuy;
        this.flag = flag;
        this.version = version;
        this.inventory = inventory;
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

    public boolean isFlag() {
        return flag;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && flag == product.flag && inventory == product.inventory && version == product.version
                && Objects.equals(codeProduct, product.codeProduct) && Objects.equals(nameProduct, product.nameProduct)
                && Objects.equals(priceSell, product.priceSell) && Objects.equals(priceBuy, product.priceBuy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codeProduct, nameProduct, priceSell, priceBuy, flag, inventory, version);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", codeProduct='" + codeProduct + '\'' +
                ", nameProduct='" + nameProduct + '\'' +
                ", priceSell=" + priceSell +
                ", priceBuy=" + priceBuy +
                ", flag=" + flag +
                ", inventory=" + inventory +
                ", version=" + version +
                '}';
    }

//    @Override
//    public int compareTo(Product product) {
//        if (this.getPriceBuy().compareTo(product.priceBuy) == 0){
//            return -this.getPriceSell().compareTo(product.priceSell);
//        }
//        return this.getPriceBuy().compareTo(product.priceBuy) ;
//    }
}
