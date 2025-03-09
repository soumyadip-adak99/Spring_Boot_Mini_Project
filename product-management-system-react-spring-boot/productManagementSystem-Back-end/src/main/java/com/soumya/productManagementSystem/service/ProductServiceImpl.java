package com.soumya.productManagementSystem.service;

import com.soumya.productManagementSystem.entity.ProductEntity;
import com.soumya.productManagementSystem.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public ProductEntity saveProduct(ProductEntity product) {
        return productRepo.save(product);
    }

    @Override
    public List<ProductEntity> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public ProductEntity getProductById(String id) {
        Optional<ProductEntity> product = productRepo.findById(id);
        return product.orElse(null); // Return null if product not found
    }

    @Override
    public void deleteProduct(String id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
        } else {
            throw new RuntimeException("Product not found");
        }
    }
}