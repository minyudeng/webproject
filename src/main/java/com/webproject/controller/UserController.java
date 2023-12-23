package com.webproject.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webproject.entity.User;
import com.webproject.mapper.UserMapper;
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

    @PostMapping("/user/update")
    public Result update(@RequestBody User user) {
        Result result;
        try {
            result = userService.update(user);
        } catch (Exception e) {
            return Result.error("更新失败", e);
        }
        return result;
    }
    @GetMapping("/user/get/list")
    public Result getMap(@RequestParam(required = false, defaultValue = "1") int pageNum,
                         @RequestParam(required = false, defaultValue = "0") int pageSize,
                         @RequestParam(required = false, defaultValue = "uid asc") String orderBy,
                         @RequestParam() String likename,
                         @RequestParam String role) {
        role = role.equals("全部") ? "" : role;
        return userService.getAllUsers(pageNum, pageSize, orderBy, likename, role);
    }

    @PostMapping("/user/update/role")
    public Result updateRole(@RequestBody Map<String, String> requestBody) {
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

    //阅读历史
    @PutMapping("/user/book-history")
    public Result addReadHistory(int bid, int uid,
                                 @RequestParam(required = false,defaultValue = "-1") int chapterId) {
        userService.addReadHistory(bid, uid,chapterId);
        return Result.success();
    }
}
