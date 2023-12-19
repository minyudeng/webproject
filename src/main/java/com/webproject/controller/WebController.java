package com.webproject.controller;

import com.webproject.entity.User;
import com.webproject.service.MailService;
import com.webproject.service.UserService;
import com.webproject.utils.Result;
import com.webproject.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        Result result = userService.login(user);
        return result;
    }
    @PostMapping("/signup")
    public Result signup(@RequestBody User user){
        Result result = userService.insert(user);
        return result;
    }
    @PutMapping("/reset/pwd")
    public Result serPwd(UserVo userVo){
        if (mailService.registered(userVo)){
            return userService.updatePwd(userVo);
        }else {
            return Result.error("验证码错误或失效，请重新获取");
        }
    }
    @PostMapping("/send-code")
    public Result code(UserVo userVo){
        System.out.println("user="+userVo);
        return mailService.sendMimeMail(userVo);
    }
}
