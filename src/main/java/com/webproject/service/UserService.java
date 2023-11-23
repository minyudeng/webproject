package com.webproject.service;


import com.webproject.entity.User;
import com.webproject.utils.Result;

import java.util.List;
import java.util.Map;

public interface UserService {
    User selectByusername(String username);


    Result insert(User user);

    Result login(User user);
    Result update(User user);
    Map<String, Object> getUserInfo(String token);
    List<User> getByLikeName(String likeName, String role);
}
