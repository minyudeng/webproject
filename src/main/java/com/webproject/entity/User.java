package com.webproject.entity;

import lombok.Data;

@Data
public class User {
    private int uid;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String signature;
    private String avatar;
    private String role;
}
