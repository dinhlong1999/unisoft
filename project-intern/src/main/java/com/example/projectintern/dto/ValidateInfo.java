package com.example.projectintern.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validateInfo",propOrder = {
        "validate"
})
public class ValidateInfo {
    private List<String> validate;

    public ValidateInfo() {
    }

    public List<String> getValidate() {
        return validate;
    }

    public void setValidate(List<String> validate) {
        this.validate = validate;
    }
}
