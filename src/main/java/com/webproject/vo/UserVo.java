package com.webproject.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVo {
    private String username;
    private String password;
    private String email;
    private String code;
}
