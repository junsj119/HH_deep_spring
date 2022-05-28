package com.sparta.week04.service;

import static org.junit.jupiter.api.Assertions.*;

import com.sparta.week04.dto.ProductMypriceRequestDto;
import com.sparta.week04.domain.Product;
import com.sparta.week04.dto.ProductRequestDto;
import com.sparta.week04.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.sparta.week04.service.ProductService.MIN_MY_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @Test
    @DisplayName("관심 상품 희망가 - 최저가 이상으로 변경")
    void updateProduct_Normal() {
// given

        Long productId = 100L;
        int myprice = MIN_MY_PRICE + 1000;


        ProductMypriceRequestDto requestMyPriceDto = new ProductMypriceRequestDto(myprice);

        Long userId = 777L;
        ProductRequestDto requestProductDto = new ProductRequestDto(
        "오리온 꼬북칩 초코츄러스맛 160g",
        "https://shopping-phinf.pstatic.net/main_2416122/24161228524.20200915151118.jpg",
        "https://search.shopping.naver.com/gate.nhn?id=24161228524",
        2350
        );

        Product product = new Product(requestProductDto, userId);       //userId 대신 productId 넣어줘도 됌. 왜지?? 나는 productId가 맞다고 보는데 흐믐

        ProductService productService = new ProductService(productRepository);
        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

// when
        Product result = productService.Mypriceupdate(productId, requestMyPriceDto);

// then
        assertEquals(myprice, result.getMyprice());
    }

    @Test
    @DisplayName("관심 상품 희망가 - 최저가 미만으로 변경")
    void updateProduct_Failed() {
// given
        Long productId = 100L;
        int myprice = MIN_MY_PRICE - 50;

        ProductMypriceRequestDto requestMyPriceDto = new ProductMypriceRequestDto(myprice);

        ProductService productService = new ProductService(productRepository);

// when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.Mypriceupdate(productId, requestMyPriceDto);
        });

// then
        assertEquals("유효하지 않은 관심 가격입니다. 최소 " + MIN_MY_PRICE + " 원 이상으로 설정해 주세요.",exception.getMessage()
        );
    }
}