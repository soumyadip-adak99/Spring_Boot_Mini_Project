package com.soumya.productManagementSystem.controller;

import com.soumya.productManagementSystem.entity.ProductEntity;
import com.soumya.productManagementSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/saveProduct")
    public ResponseEntity<?> saveProduct(
            @RequestBody
            ProductEntity product
    ) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct() {
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<?> getProductById(
            @PathVariable
            String id
    ) {
        ProductEntity product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        ProductEntity product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editProduct/{id}")
    public ResponseEntity<?> editProduct(
            @PathVariable
            String id,
            @RequestBody
            ProductEntity newProduct
    ) {
        ProductEntity searchProduct = productService.getProductById(id);
        if (searchProduct != null) {
            searchProduct.setProductName(newProduct.getProductName());
            searchProduct.setProductDescription(newProduct.getProductDescription());
            searchProduct.setPrice(newProduct.getPrice());
            searchProduct.setStatus(newProduct.getStatus());
            return new ResponseEntity<>(productService.saveProduct(searchProduct), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }
}