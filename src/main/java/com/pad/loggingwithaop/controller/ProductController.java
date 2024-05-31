package com.pad.loggingwithaop.controller;

import com.pad.loggingwithaop.exception.ResourceNotFoundException;
import com.pad.loggingwithaop.model.Product;
import com.pad.loggingwithaop.payload.ResponseDTO;
import com.pad.loggingwithaop.repository.ProductRepository;
import com.pad.loggingwithaop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseDTO getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public ResponseDTO getListProduct(@PathVariable("productId") Long productId) throws ResourceNotFoundException {
        return productService.getProductById(productId);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseDTO updateProduct(@RequestBody Product product,
                                     @PathVariable("productId") Long productId) throws ResourceNotFoundException {
        return productService.updateProduct(product, productId);
    }
    @DeleteMapping("/products/{id}")
    public ResponseDTO deleteProduct(@PathVariable("id") Long productId) throws ResourceNotFoundException {
        return productService.deleteProduct(productId);
    }
}
