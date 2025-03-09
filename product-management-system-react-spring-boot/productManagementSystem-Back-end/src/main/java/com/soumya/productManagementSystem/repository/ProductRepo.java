package com.soumya.productManagementSystem.repository;

import com.soumya.productManagementSystem.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<ProductEntity, String> {
}
