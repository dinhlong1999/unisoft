package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "idEmployee","codeProduct","customerName",
        "customerPhoneNumber","dateStart","dateEnd"
})
@XmlRootElement(name = "getOrderDetailByUserRequest")
public class GetOrderDetailByUserRequest {
    @XmlElement(required = true)
    private int idEmployee;
    @XmlElement(required = true)
    private String codeProduct;
    @XmlElement(required = true)
    private String customerName;
    @XmlElement(required = true)
    private String customerPhoneNumber;
    @XmlElement(required = true)
    private String dateStart;
    @XmlElement(required = true)
    private String dateEnd;

    public GetOrderDetailByUserRequest() {
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
