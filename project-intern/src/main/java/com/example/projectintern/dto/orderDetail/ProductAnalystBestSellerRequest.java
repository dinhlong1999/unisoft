package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "analyst"
})
@XmlRootElement(name = "productAnalystBestSellerRequest")
public class ProductAnalystBestSellerRequest {
    @XmlElement(required = true)
    private AnalystRequest analyst;

    public ProductAnalystBestSellerRequest() {
    }

    public AnalystRequest getAnalyst() {
        return analyst;
    }

    public void setAnalyst(AnalystRequest analyst) {
        this.analyst = analyst;
    }
}
