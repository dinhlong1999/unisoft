package com.example.projectintern.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "validateInfo"
})
@XmlRootElement(name = "getValidateInfoResponse")
public class GetValidateInfoResponse {
    private ValidateInfo validateInfo;

    public GetValidateInfoResponse() {
    }

    public ValidateInfo getValidateInfo() {
        return validateInfo;
    }

    public void setValidateInfo(ValidateInfo validateInfo) {
        this.validateInfo = validateInfo;
    }
}
