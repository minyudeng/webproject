package com.webproject.controller;

import com.webproject.entity.User;
import com.webproject.service.UserService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @Autowired
    UserService userService;

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
}
