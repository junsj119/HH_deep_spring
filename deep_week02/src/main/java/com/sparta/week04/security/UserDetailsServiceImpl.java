package com.sparta.week04.security;

import com.sparta.week04.domain.Users;
import com.sparta.week04.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {     //Spring Security에서 유저의 정보를 가져오는 인터페이스이다.

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //loadUserByUsername 는 UserDetailsService가 가지고 있는 함수
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));
        //조회가 되면 UserDetail로 반환?
        return new UserDetailsImpl(user);
    }
}
