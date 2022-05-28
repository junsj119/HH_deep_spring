package com.sparta.week04.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {
    private String username;
    private String password;
    private String email;
    private UserRoleEnum role;

    @BeforeEach
        //하기 전에 얘부터 실행시켜준다.
    void setup() {
        username = "1";
        password = "2";
        email = "3";
        role = UserRoleEnum.valueOf("USER");
    }

        @Test
        @DisplayName("회원가입")
        void Userinput() {
            Users users = new Users(username, password, email, role);

            assertEquals(username, users.getUsername());
            assertEquals(password, users.getPassword());
            assertEquals(email, users.getEmail());
            assertEquals(role, users.getRole());
        }
}