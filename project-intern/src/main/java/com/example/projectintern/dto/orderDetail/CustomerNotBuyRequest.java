package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "analystRequest"
})
@XmlRootElement(name = "customerNotBuyRequest")
public class CustomerNotBuyRequest {

    @XmlElement(required = true)
    private AnalystRequest analystRequest;

    public CustomerNotBuyRequest() {
    }

    public AnalystRequest getAnalystRequest() {
        return analystRequest;
    }

    public void setAnalystRequest(AnalystRequest analystRequest) {
        this.analystRequest = analystRequest;
    }
}
