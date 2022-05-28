package com.sparta.week04.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true)  //@secured 어노테이션 활성화
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure (WebSecurity web){
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
        //http.csrf().ignoringAntMatchers("/user/**");

        //POST 요청들이 문제없이 처리된다. csef 무시
        http.csrf().disable();

        http.authorizeRequests()
                    // image 폴더를 login 없이 허용
                    .antMatchers("/images/**").permitAll()
                    // css 폴더를 login 없이 허용
                    .antMatchers("/css/**").permitAll()
                    //회원 관리 처리 API 전부를 login 없이 허용
                    .antMatchers("/user/**").permitAll()

                     // 어떤 요청이든 '인증'
                     .anyRequest().authenticated()
                .and()

                    // 로그인 기능 허용
                    .formLogin()

                    //로그인 View 제공(GET/user/login)
                    .loginPage("/user/login")   //로그인 할 때 longin.html 페이지로
                    // 로그인 처리 (POST/user/login)
                    .loginProcessingUrl("/user/login")  //로그인 처리(보안검색대 가고 그런 과정들)

                    .defaultSuccessUrl("/")     //로그인이 성공할 시 해당 url로 이동
                    .failureUrl("/user/login?error")    //실패했을 때 url
                    .permitAll()//로그인에 관련된 기능에 대해 허용을 해줘라
                .and()

                    // 로그아웃 기능 허용
                    .logout()
                    // 로그아웃 처리 URL
                    .logoutUrl("/user/logout")
                    .permitAll()           //로그아웃에 관련된 기능에 대해 허용을 해줘라
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/forbidden.html");   //접근 불가 페이지 설정.
    }
}