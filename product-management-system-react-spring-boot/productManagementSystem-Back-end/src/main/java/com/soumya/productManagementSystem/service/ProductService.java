package com.soumya.productManagementSystem.service;

import com.soumya.productManagementSystem.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    public ProductEntity saveProduct(ProductEntity product);

    public List<ProductEntity> getAllProduct();

    public ProductEntity getProductById(String id);

    public void deleteProduct(String id);
}
