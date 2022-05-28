package com.sparta.week04.controller;

import com.sparta.week04.dto.ItemDto;
import com.sparta.week04.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor // final 로 선언된 클래스를 자동으로 생성합니다.
@RestController // JSON으로 응답함을 선언합니다. //스프링이 알아서 해줘~
public class SearchRequestController {

    private final NaverShopSearch naverShopSearch;

    @GetMapping("/api/search")      //키워드로 상품 검색하고 그 결과를 목록으로 보여주기   ?query= abcd 가 있는데 abcd가 @RequestParam String query 한테 들어간다.
    public List<ItemDto>execSearch(@RequestParam String query){
        String result = naverShopSearch.search(query);
        return naverShopSearch.fromJSONtoItems(result);
    }
}