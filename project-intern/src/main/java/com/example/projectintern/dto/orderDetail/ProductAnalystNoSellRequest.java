package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "analyst"
})
@XmlRootElement(name = "productAnalystNoSellRequest")
public class ProductAnalystNoSellRequest {
    @XmlElement(required = true)
    private AnalystRequest analyst;

    public ProductAnalystNoSellRequest() {
    }

    public AnalystRequest getAnalyst() {
        return analyst;
    }

    public void setAnalyst(AnalystRequest analyst) {
        this.analyst = analyst;
    }
}
