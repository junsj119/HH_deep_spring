package com.sparta.week04.controller;

import com.sparta.week04.domain.Product;
import com.sparta.week04.domain.UserRoleEnum;
import com.sparta.week04.dto.ProductMypriceRequestDto;
import com.sparta.week04.repository.ProductRepository;
import com.sparta.week04.dto.ProductRequestDto;
import com.sparta.week04.security.UserDetailsImpl;
import com.sparta.week04.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController// JSON으로 데이터를 주고받음을 선언합니다.
@RequiredArgsConstructor
@Slf4j
public class ProductRestController {

    //private final ProductRepository productRepository;
    private final ProductService productService;


//    @GetMapping("/api/products")
//    public List<Product> readProducts(){
//        return productRepository.findAll();
//    }
    //등록된 상품들 보여주기
    @GetMapping("/api/products")
    public List<Product> readProducts(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        return productService.getProduct(userId);
    }

    //상품 등록
    @PostMapping("/api/products")           //입력받은 값들을 Product생성자를 통해 넣어주고 그 객체?를 레포지토리에 넣어서 저장한다.
    public Product createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        Long userId = userDetails.getUser().getId();

//        Product product = new Product(requestDto, userId);  //비영속상태
//        return productRepository.save(product);

        Product product = productService.createProduct(requestDto, userId);
        // 응답 보내기
        return product;
    }

    //가격 변경
    @PutMapping("/api/products/{id}")
    public void mypriceSet(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto){
        //log.info("products.mypriceSet");
        productService.Mypriceupdate(id, requestDto);

    }

    //관리자 상품 조회
    @Secured(UserRoleEnum.Authority.ADMIN)
    @GetMapping("/api/admin/products")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
}
