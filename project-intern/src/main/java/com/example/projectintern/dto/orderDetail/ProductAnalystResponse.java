package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "productAnalyst","errorList"
})
@XmlRootElement(name = "productAnalystResponse")
public class ProductAnalystResponse {
    @XmlElement(required = true)
    private List<ProductAnalyst> productAnalyst;

    @XmlElement(required = true)
    private List<String> errorList;

    public ProductAnalystResponse() {
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<ProductAnalyst> getProductAnalyst() {
        return productAnalyst;
    }

    public void setProductAnalyst(List<ProductAnalyst> productAnalyst) {
        this.productAnalyst = productAnalyst;
    }
}
