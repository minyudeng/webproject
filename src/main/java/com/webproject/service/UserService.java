package com.webproject.service;


import com.webproject.entity.User;
import com.webproject.utils.Result;

import java.util.List;
import java.util.Map;

public interface UserService {
    public User selectByusername(String username);


    public Result insert(User user);

    public Result login(User user);
    public Result update(User user);
    public Map<String, Object> getUserInfo(String token);
    public List<User> getByLikeName(String likeName, String role);
    public boolean setRole(String role, int uid);
}
