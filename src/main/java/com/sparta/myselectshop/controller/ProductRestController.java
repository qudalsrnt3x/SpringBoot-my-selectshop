package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.domain.Product;
import com.sparta.myselectshop.domain.ProductMypriceRequestDto;
import com.sparta.myselectshop.domain.ProductRepository;
import com.sparta.myselectshop.domain.ProductRequestDto;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class ProductRestController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = new Product(requestDto);
        return productRepository.save(product);
    }

    @PutMapping("/api/product/{id}")
    public Long update(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.update(id, requestDto);
    }

    @DeleteMapping("/api/product/{id}")
    public Long delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return id;
    }
}
