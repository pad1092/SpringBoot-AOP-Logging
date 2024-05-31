package com.pad.loggingwithaop.service;

import com.pad.loggingwithaop.exception.ResourceNotFoundException;
import com.pad.loggingwithaop.model.Product;
import com.pad.loggingwithaop.payload.ResponseDTO;
import com.pad.loggingwithaop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ResponseDTO getAllProducts(){
        List<Product> productList = productRepository.findAll();
        return ResponseDTO.builder().detail(productList).build();
    }

    public ResponseDTO getProductById(Long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find product with id: " + productId));

        ResponseDTO responseDTO = ResponseDTO.builder().detail(product).build();
        return responseDTO;
    }
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public ResponseDTO deleteProduct(Long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find product with id: " + productId));;
        productRepository.delete(product);
        return ResponseDTO.builder().build();
    }
    public ResponseDTO updateProduct(Product productDetails, Long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find product with id: " + productId));

        product.setCode(productDetails.getCode());
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product = productRepository.save(product);

        return ResponseDTO.builder().detail(product).build();
    }
}
