package com.sparta.myselectshop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 회원 아이디 별로 상품 조회
    List<Product> findAllByUserId(Long userId);
}
