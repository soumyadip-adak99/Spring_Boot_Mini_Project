package com.soumya.productManagementSystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "product_data")
@Data
@NoArgsConstructor
public class ProductEntity {

    @Id
    private String id;

    private String productName;
    private String productDescription;
    private Double price;
    private String status;
}
