package com.sparta.week04.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ProductMypriceRequestDto {
    private int myprice;

    public ProductMypriceRequestDto(){

    }
    public ProductMypriceRequestDto(int myprice){
        this.myprice = myprice;
    }
}

/*
* @Getter
public class ProductMypriceRequestDto {
    private int myprice;

    public ProductMypriceRequestDto(int myprice){
        this.myprice = myprice;
    }
}

* */

