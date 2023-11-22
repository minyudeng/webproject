package com.webproject.controller;

import com.webproject.entity.User;
import com.webproject.service.UserService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/update")
    public Result update(@RequestBody User user){
        Result result;
        try {
            result = userService.update(user);
        } catch (Exception e) {
            return Result.error("更新失败", e);
        }
        return result;
    }

    @GetMapping("/getByLikeName")
    public Result getByLikeName(@RequestParam String likename,@RequestParam String role){
        System.out.println("查询:" +likename+"  "+ role);
        List<User> userList;
        role = role.equals("全部") ? "" : role;
        try {
            userList = userService.getByLikeName(likename,role);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("查询失败",e);
        }
        return Result.success(userList);
    }

    @PostMapping("/updaterole")
    public Result updateRole(@RequestBody Map<String, String> requestBody){
        Result result;
        User user = userService.selectByusername(requestBody.get("username"));
        user.setRole(requestBody.get("role"));
        try {
            result = userService.update(user);
        } catch (Exception e) {
            return Result.error("更新失败", e);
        }
        return result;
    }
}
