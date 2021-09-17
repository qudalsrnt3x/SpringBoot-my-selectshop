package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.domain.Product;
import com.sparta.myselectshop.domain.ProductMypriceRequestDto;
import com.sparta.myselectshop.domain.ProductRepository;
import com.sparta.myselectshop.domain.ProductRequestDto;
import com.sparta.myselectshop.domain.user.User;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class ProductRestController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    // 상품 조회 (사용자)
    @GetMapping("/api/products")
    public List<Product> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return productRepository.findAllByUserId(userId);
    }

    // 상품 조회 (관리자)
    @GetMapping("/api/admin/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 신규 상품 등록
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 ID
        Long userId = userDetails.getUser().getId();

        Product product = new Product(requestDto, userId);
        return productRepository.save(product);
    }

    @PutMapping("/api/products/{id}")
    public Long update(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.update(id, requestDto);
    }

    @DeleteMapping("/api/product/{id}")
    public Long delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return id;
    }
}
