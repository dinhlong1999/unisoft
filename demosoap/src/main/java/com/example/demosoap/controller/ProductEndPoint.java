package com.example.demosoap.controller;

import com.example.demosoap.interfaces.*;
import com.example.demosoap.model.Product;
import com.example.demosoap.model.TypeProduct;
import com.example.demosoap.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapFaultException;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;

import java.util.List;


@Endpoint
public class ProductEndPoint {
    private static final String NAMESPACE_URI = "http://interfaces.soap.springboot.vkakarla.com";

    @Autowired
    private IProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest productRequest) {
        GetProductResponse response = new GetProductResponse();
        ProductInfo productInfo = new ProductInfo();
        Product product = productService.getProductByID(productRequest.getProductId());
        BeanUtils.copyProperties(product, productInfo);
        response.setProductInfo(productInfo);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductRequest")
    @ResponsePayload
    public DeleteProductResponse deleteProduct(@RequestPayload DeleteProductRequest deleteProductRequest) {
        Product product = productService.getProductByID(deleteProductRequest.getProductId());
        if (product == null) {
            SoapFaultDefinition soapFaultDefinition = new SoapFaultDefinition();
            soapFaultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
            soapFaultDefinition.setFaultStringOrReason("Product not found");

            throw new SoapFaultException(String.valueOf(soapFaultDefinition));
        }
        productService.deleteProductByID(deleteProductRequest.getProductId());
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Content Deleted Successfully");
        DeleteProductResponse deleteProductResponse = new DeleteProductResponse();
        deleteProductResponse.setServiceStatus(serviceStatus);
        return deleteProductResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductRequest")
    @ResponsePayload
    public GetAllProductResponse getList(@RequestPayload GetAllProductRequest getAllProductRequest) {
        List<Product> productList = productService.getListProduct();
        GetAllProductResponse response = new GetAllProductResponse();
        for (Product product : productList) {
            ProductInfo productInfo = new ProductInfo();
            BeanUtils.copyProperties(product, productInfo);
            response.getProducts().add(productInfo);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductRequest")
    @ResponsePayload
    public UpdateProductResponse updateProduct(@RequestPayload UpdateProductRequest request) {
        Product product = productService.getProductByID(request.getProductInfo().getId());
        if (product == null) {
            SoapFaultDefinition soapFaultDefinition = new SoapFaultDefinition();
            soapFaultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
            soapFaultDefinition.setFaultStringOrReason("Product not found");

            throw new SoapFaultException(String.valueOf(soapFaultDefinition));
        }
        product.setName(request.getProductInfo().getName());
        product.setPrice(request.getProductInfo().getPrice());
        TypeProduct typeProduct = new TypeProduct();
        BeanUtils.copyProperties(request.getProductInfo().getTypeProduct(),typeProduct);
        product.setTypeProduct(typeProduct);
        productService.updateProduct(product);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Content Update Successfully");
        UpdateProductResponse response = new UpdateProductResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }


}
