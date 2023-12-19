package com.webproject.service;


import com.github.pagehelper.Page;
import com.webproject.entity.User;
import com.webproject.utils.Result;
import com.webproject.vo.UserVo;

import java.util.List;
import java.util.Map;

public interface UserService {
    Result getAllUsers(int pageNum, int pageSize, String order,String likeName,String role);
    User selectByusername(String username);

    Result insert(User user);

    Result login(User user);
    Result update(User user);
    Map<String, Object> getUserInfo(String token);
    void addReadHistory(int bid, int uid);
    Result updatePwd(UserVo userVo);
}
