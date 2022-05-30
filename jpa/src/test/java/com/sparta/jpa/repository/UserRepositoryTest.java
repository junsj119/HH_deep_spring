package com.sparta.jpa.repository;

import com.sparta.jpa.domain.Users;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Order(1)
    @Test
    public void create() {
// 회원 "user1" 객체 생성
        Users instance1 = new Users("user1", "정국", "불족발");
// 회원 "user1" 객체 또 생성
        Users instance2 = new Users("user1", "정국", "불족발");
        assert(instance2 != instance1);
// 회원 "user1" 객체 또또 생성
        Users instance3 = new Users("user1", "정국", "불족발");
        assert(instance3 != instance2);

// 회원 "user1" 객체 추가
        Users user1 = new Users("user1", "정국", "불족발");

// 회원 "user1" 객체의 ID 값이 없다가..
        userRepository.save(user1);

// 테스트 회원 데이터 삭제
        userRepository.delete(user1);
    }

    @Order(2)
    @Test
    public void findUser() {
        //다 다르다. 1차캐시는 spring 내부에서만 동작한다. 이 친구들을 test코드에 있어서 다르게 나옴.
// ------------------------------------
// 회원 "user1" 객체 추가
        Users beforeSavedUser = new Users("user1", "정국", "불족발");

// 회원 "user1" 객체를 영속화
        Users savedUser = userRepository.save(beforeSavedUser);

// 회원 "user1" 을 조회
        Users foundUser1 = userRepository.findById("user1").orElse(null);
        assert(foundUser1 != savedUser);

// 회원 "user1" 을 또 조회
        Users foundUser2 = userRepository.findById("user1").orElse(null);
        assert(foundUser2 != savedUser);

// 회원 "user1" 을 또또 조회
        Users foundUser3 = userRepository.findById("user1").orElse(null);
        assert(foundUser3 != savedUser);

// ------------------------------------
// 테스트 회원 데이터 삭제
        userRepository.delete(beforeSavedUser);
    }
}