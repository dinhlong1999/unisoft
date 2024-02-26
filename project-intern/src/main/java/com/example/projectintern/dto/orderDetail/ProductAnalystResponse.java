package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "productAnalyst"
})
@XmlRootElement(name = "productAnalystResponse")
public class ProductAnalystResponse {
    @XmlElement(required = true)
    private List<ProductAnalyst> productAnalyst;

    public ProductAnalystResponse() {
    }

    public List<ProductAnalyst> getProductAnalyst() {
        return productAnalyst;
    }

    public void setProductAnalyst(List<ProductAnalyst> productAnalyst) {
        this.productAnalyst = productAnalyst;
    }
}
