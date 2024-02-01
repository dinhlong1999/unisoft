package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "codeProduct","nameProduct","quantity"
})
@XmlRootElement(name = "allocationRequest")
public class AllocationRequest {

    @XmlElement(required = true)
    private String codeProduct;
    @XmlElement(required = true)
    private String nameProduct;
    @XmlElement(required = true)
    private String quantity;

    public AllocationRequest() {
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
