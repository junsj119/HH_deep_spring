package com.sparta.week04.domain;

import com.sparta.week04.dto.ItemDto;
import com.sparta.week04.dto.ProductMypriceRequestDto;
import com.sparta.week04.dto.ProductRequestDto;
import com.sparta.week04.validator.ProductValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Product {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    @Column(nullable = false)
    private Long userId;

    // 관심 상품 생성 시 이용합니다.
    public Product(ProductRequestDto requestDto, Long userId) {

//        ProductValidator productValidator = new ProductValidator();
//        productValidator.validateProductInput(requestDto, userId);
        ProductValidator.validateProductInput(requestDto, userId);  //static으로 선언하면 클래스 이름을 가져다가 쓸 수 있다.



// 관심상품을 등록한 회원 Id 저장
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0;
    }



    public void update(ProductMypriceRequestDto requestDto) {
        this.myprice = requestDto.getMyprice();
    }

    public void updateByItemDto(ItemDto itemDto){
        this.lprice = getLprice();
    }
}