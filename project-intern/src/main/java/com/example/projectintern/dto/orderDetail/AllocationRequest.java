package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "productCode","quantity"
})
@XmlRootElement(name = "allocationRequest")
public class AllocationRequest {
    @XmlElement(required = true)
    private String productCode;
    @XmlElement(required = true)
    private int quantity;

    public AllocationRequest() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
