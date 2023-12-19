package com.webproject.service;

import com.webproject.utils.Result;
import com.webproject.vo.UserVo;
import jakarta.servlet.http.HttpSession;

public interface MailService {
    Result sendMimeMail(UserVo userVo);
    String randomCode();
    boolean registered(UserVo userVo);
}
