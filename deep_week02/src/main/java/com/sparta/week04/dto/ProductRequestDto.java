package com.sparta.week04.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequestDto {
    private String title;
    private String link;
    private String image;
    private int lprice;

    public ProductRequestDto(String title, String image, String link, int lprice){
        this.title = title;
        this.image = image;
        this.link = link;
        this.lprice = lprice;
    }
}
