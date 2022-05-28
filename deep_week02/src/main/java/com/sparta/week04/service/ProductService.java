package com.sparta.week04.service;

import com.sparta.week04.dto.ItemDto;
import com.sparta.week04.domain.Product;
import com.sparta.week04.dto.ProductMypriceRequestDto;
import com.sparta.week04.dto.ProductRequestDto;
import com.sparta.week04.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public static final int MIN_MY_PRICE = 100;

    //상품 등록
    @Transactional // DB정보가 업데이트 된다.
    public Product createProduct(ProductRequestDto requestDto, Long userId ) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto, userId);

        productRepository.save(product);

        return product;
    }

    //가격 변경
    @Transactional // DB정보가 업데이트 된다.
    public Product Mypriceupdate(Long id, ProductMypriceRequestDto requestDto){

        int myprice = requestDto.getMyprice();
        if (myprice < MIN_MY_PRICE) {
            throw new IllegalArgumentException("유효하지 않은 관심 가격입니다. 최소 " + MIN_MY_PRICE + " 원 이상으로 설정해 주세요.");
        }

        Product product = productRepository.findById(id).orElseThrow(
            ()-> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        product.update(requestDto);
        return product;
    }


    //스케쥴러에 있다. 상품 정보 업데이트
    @Transactional // DB정보가 업데이트 된다.
    public void updateBySearch(Long id, ItemDto itemDto){

        Product product = productRepository.findById(id).orElseThrow(
                ()->new NullPointerException("아이디가 존재하지 않습니다.")
        );
        product.updateByItemDto(itemDto);
    }

    //ID별 등록 상품 조회
    @Transactional // DB정보가 업데이트 된다.
    public List<Product> getProduct(Long userId){
        return productRepository.findAllByUserId(userId);
    }

    //관리자용 상품 전체 조회
    @Transactional // DB정보가 업데이트 된다.
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
}
