package com.sparta.week04.mockobject;

import com.sparta.week04.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockProductRepository {

    private List<Product> products = new ArrayList<>();
    // 상품 테이블 ID: 1부터 시작
    private Long productId = 1L;

    // 상품 저장
    public Product save(Product product) {
        for (Product existProduct : products) {
// 이미 저장된 상품 -> 희망 최저가 업데이트
            if (existProduct.getId().equals(product.getId())) {     //들어온 친구가 원래 있었다면 상품 최저가만 업데이트
                int myprice = product.getMyprice();
                existProduct.setMyprice(myprice);
                return existProduct;
            }
        }

// 신규 상품 -> DB 에 저장         id를 셋팅해준 후 + 해주고  products list 즉 db에 저장한다. +는 다음 타자를 위해?
        product.setId(productId);
        products.add(product);

        ++productId;
        return product;
    }

    // 상품 ID 로 상품 조회
    public Optional<Product> findById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return Optional.of(product);
            }
        }

        return Optional.empty();
    }

    // 회원 ID 로 등록된 상품 조회
    public List<Product> findAllByUserId(Long userId) {
        List<Product> userProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getId().equals(userId)) {
                userProducts.add(product);
            }
        }

        return userProducts;
    }

    // (관리자용) 상품 전체 조회
    public List<Product> findAll() {
        return products;
    }
}