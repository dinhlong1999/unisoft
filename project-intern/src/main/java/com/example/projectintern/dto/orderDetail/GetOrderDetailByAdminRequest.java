package com.example.projectintern.dto.orderDetail;


import javax.xml.bind.annotation.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "accountName",
        "employeeName",
        "codeProduct",
        "customerName",
        "customerPhoneNumber",
        "dateStart",
        "dateEnd"
})
@XmlRootElement(name = "getOrderDetailByAdminRequest")
public class GetOrderDetailByAdminRequest {
    @XmlElement(required = true)
    private String accountName;

    @XmlElement(required = true)
    private String employeeName;
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


    public GetOrderDetailByAdminRequest() {
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public void String(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

}
