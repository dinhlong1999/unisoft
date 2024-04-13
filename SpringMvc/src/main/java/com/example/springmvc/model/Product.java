package com.example.springmvc.model;



public class Product {

    private int id;

    private String codeProduct;

    private String nameProduct;

    private Double priceSell;

    private Double priceBuy;
    
    private boolean flag;

    private int inventory;

    private int version;
    
    public Product(int id, String codeProduct, String nameProduct, Double priceSell, Double priceBuy, boolean flag,
                   int version,int inventory) {
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

}
