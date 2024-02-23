package com.example.projectintern.dto.product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productInfo", propOrder = {
        "id","codeProduct","nameProduct","priceSell","priceBuy","flag","version","inventory"
})
public class ProductInfo  {
    private int id;
    @NotBlank(message = "Mã sản phẩm không được để trống")
    @Pattern(regexp ="^PR-\\d{4}$",message = "Không đúng định dạng")
    private String codeProduct;
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String nameProduct;

    @NotNull(message = "Giá bán sản phẩm không được để trống")
    private double priceSell;
    @NotNull(message = "Giá mua sản phẩm không được để trống")
    private double priceBuy;
    private boolean flag;
    private int version;
    private int inventory;

    public ProductInfo() {
    }

    public ProductInfo(int id,
                       String codeProduct,
                       String nameProduct,
                       double priceSell,
                       double priceBuy,
                       boolean flag,
                       int version,
                       int inventory) {
        this.id = id;
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.priceSell = priceSell;
        this.priceBuy = priceBuy;
        this.flag = flag;
        this.version = version;
        this.inventory = inventory;
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

    public double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(double priceSell) {
        this.priceSell = priceSell;
    }

    public double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public boolean isFlag() {
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

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
