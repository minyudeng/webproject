package com.webproject.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Code {
    private String username;
    private String email;
    private String code;
    private LocalDateTime time;
}
