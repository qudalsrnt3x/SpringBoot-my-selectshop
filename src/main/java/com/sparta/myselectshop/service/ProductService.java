package com.sparta.myselectshop.service;

import com.sparta.myselectshop.domain.Product;
import com.sparta.myselectshop.domain.ProductMypriceRequestDto;
import com.sparta.myselectshop.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long update(Long id, ProductMypriceRequestDto requestDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        product.update(requestDto);
        return id;
    }
}
