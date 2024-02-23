package com.example.projectintern.controller;

import com.example.projectintern.dto.IProductDetail;
import com.example.projectintern.dto.ServiceStatus;
import com.example.projectintern.dto.product.*;
import com.example.projectintern.model.Product;
import com.example.projectintern.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.validation.Valid;
import javax.xml.soap.SOAPException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Endpoint
public class ProductEndPoint {

    private static final String NAMESPACE_URI = "http://interfaces.soap.springboot.vkakarla.com";

    @Autowired
    private IProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductRequest")
    @ResponsePayload
    public GetAllProductResponse getAllProduct(@RequestPayload GetAllProductRequest request) {
        if (request.getCodeProduct() == null) {
            request.setCodeProduct("");
        }
        if (request.getNameProduct() == null) {
            request.setNameProduct("");
        }
//        Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());
//        Page<IProductDetail> productList = productService.getAllProduct(pageable, request.getCodeProduct(), request.getNameProduct());

        int totalRecord = productService.getTotalRecordProduct(request.getCodeProduct(), request.getNameProduct());
        double temp = (double) totalRecord / request.getLimit();
        int totalPages = (int) Math.ceil(temp);
        List<IProductDetail> productList = productService.getAllProductByNameProductAndCodeProduct(
                request.getCodeProduct(), request.getNameProduct(), request.getLimit(), (request.getPage() * request.getLimit()));
        List<ProductInfo> productInfos = new ArrayList<>();
        GetAllProductResponse response = new GetAllProductResponse();
        Supplier<ProductInfo> productInfoSupplier = ProductInfo::new;
        for (IProductDetail product : productList) {
            ProductInfo productInfo = productInfoSupplier.get();
            BeanUtils.copyProperties(product, productInfo);
            productInfos.add(productInfo);
        }
        response.setProducts(productInfos);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveProductRequest")
    @ResponsePayload
    @Secured("ROLE_ADMIN")
    public SaveProductResponse saveProduct(@RequestPayload @Valid SaveProductRequest request) {
        Product productCheckCode = productService.findProductByCodeProduct(request.getProduct().getCodeProduct());
        Product productCheckName = productService.findProductByNameProduct(request.getProduct().getNameProduct());
        SaveProductResponse response = new SaveProductResponse();
        ServiceStatus status = new ServiceStatus();
        if (productCheckName != null) {
            status.setStatus("FAILED");
            status.setMessage("Tên sản phẩm không được trùng");
            response.setServiceStatus(status);
            return response;
        }
        if (productCheckCode != null) {
            status.setStatus("FAILED");
            status.setMessage("Mã sản phẩm không được trùng");
            response.setServiceStatus(status);
            return response;
        }

        Product product = new Product();
        BeanUtils.copyProperties(request.getProduct(), product);
        productService.saveProduct(product);
        status.setStatus("TRUE");
        response.setServiceStatus(status);
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductRequest")
    @ResponsePayload
    @Secured("ROLE_ADMIN")
    public UpdateProductResponse updateProduct(@RequestPayload UpdateProductRequest request) throws SOAPException {
        Product product = productService.findProductById(request.getProduct().getId());
        ServiceStatus serviceStatus = new ServiceStatus();
        UpdateProductResponse response = new UpdateProductResponse();
        if (product == null) {
            SOAPException soapException = new SOAPException("Không tìm thấy sản phẩm");
            throw soapException;
        }
        List<Product> productList = productService.findAll();
        for (Product product1 : productList) {
            if (request.getProduct().getNameProduct().equals(product1.getNameProduct())) {
                if (request.getProduct().getId() != product1.getId()) {
                    serviceStatus.setMessage("Không được trùng tên");
                    serviceStatus.setStatus("FAILED");
                    response.setServiceStatus(serviceStatus);
                    return response;
                }
            }
            if (request.getProduct().getCodeProduct().equals(product1.getCodeProduct())) {
                if (request.getProduct().getId() != product1.getId()) {
                    serviceStatus.setStatus("FAILED");
                    response.setServiceStatus(serviceStatus);
                    return response;
                }
            }
        }
        Product productResult = new Product();
        BeanUtils.copyProperties(request.getProduct(), productResult);
        int productCheckIsUpdate = productService.updateProduct(productResult);
        if (productCheckIsUpdate == 1){
            serviceStatus.setStatus("TRUE");
        }else {
            serviceStatus.setStatus("FALSE");
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }






}
