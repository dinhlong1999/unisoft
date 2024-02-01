package com.example.demosoap.controller;

import com.example.demosoap.interfaces.GetProductRequest;
import com.example.demosoap.interfaces.GetProductResponse;
import com.example.demosoap.interfaces.ProductInfo;
import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {
    }

    public GetProductRequest createGetProductById(){
        return new GetProductRequest();
    }

    public ProductInfo createProductInfo(){
        return new ProductInfo();
    }
    public GetProductResponse getProductResponse(){
        return new GetProductResponse();
    }

}
