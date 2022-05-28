package com.sparta.week04.domain;


public enum UserRoleEnum {
    USER(Authority.USER), // 사용자 권한
    ADMIN(Authority.ADMIN); // 관리자 권한

    private final String authority;  //나중에 authority 값을 빼기 위해 사용
    UserRoleEnum(String authroity){
        this.authority = authroity;
    }

    public String getAuthority(){
        return this.authority;
    }

    public static class Authority{
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}