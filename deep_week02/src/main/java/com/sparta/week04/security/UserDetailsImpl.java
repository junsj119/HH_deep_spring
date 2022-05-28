package com.sparta.week04.security;
import com.sparta.week04.domain.UserRoleEnum;
import com.sparta.week04.domain.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {   //Spring Security에서 사용자의 정보를 담는 인터페이스이다.?


    private final Users user;

    public UserDetailsImpl(Users user) {    //userDatialsService에서 조회가 된 회원 정보의 객체
        this.user = user;
    }

    public Users getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override                                                           //getAuthorities 안에?? getAuthorities  가 있다.
    public Collection<? extends GrantedAuthority>getAuthorities(){      //GrantedAuthority 얘 인터페이스로 만들어진 애들만 인정 ex) SimpleGrantedAuthority
        UserRoleEnum role = user.getRole();     //ADMIN, USER ??
        String authority = role.getAuthority(); //"ROLE_ADMIN", "ROLE_ADMIN"

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

}