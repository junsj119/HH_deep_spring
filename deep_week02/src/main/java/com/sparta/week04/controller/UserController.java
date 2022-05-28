package com.sparta.week04.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.week04.domain.UserRoleEnum;
import com.sparta.week04.dto.KakaoUserInfoDto;
import com.sparta.week04.dto.SignupRequestDto;
import com.sparta.week04.dto.UserInfoDto;
import com.sparta.week04.security.UserDetailsImpl;
import com.sparta.week04.service.KakaoUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.sparta.week04.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@Controller
public class UserController {
//왜 @Transactional을 안써주나
    private final UserService userService;
    private final KakaoUserService kakaoUserService;
// @RequiredArgsConstructor 이 있으면 삭제 가능
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {   //@RequestBody를 왜 안넣어주나?? qnxduwnaus 415 error 아마 client 에서 json으로 안줘서 그런듯
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    // 회원 관련 정보 받기
    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        return new UserInfoDto(username, isAdmin);
    }


    @GetMapping("user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {        //스프링아 해줘
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}