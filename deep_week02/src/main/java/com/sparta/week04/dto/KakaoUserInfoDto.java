package com.sparta.week04.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class KakaoUserInfoDto {
    private Long id;
    private String nickname;
    private String email;


    //@AllArgsConstructor 해주면 된다.
//    public KakaoUserInfoDto(Long id, String nickname, String email){
//        this.id = id;
//        this.nickname = nickname;
//        this.email = email;
//    }
}
