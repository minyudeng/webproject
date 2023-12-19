package com.webproject.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webproject.entity.User;
import com.webproject.mapper.ShelfMapper;
import com.webproject.mapper.UserMapper;
import com.webproject.service.UserService;
import com.webproject.utils.JwtUtil;
import com.webproject.utils.Result;
import com.webproject.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ShelfMapper shelfMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Transactional
    @Override
    public Result insert(User user) {
        if (selectByusername(user.getUsername()) != null){
            return Result.error("该用户名已存在");
        }
        user.setRole("用户");
        user.setNickname(user.getUsername());
        try {
            userMapper.insert(user);
        } catch (Exception e){
            throw new RuntimeException("注册失败");
        }
        return Result.success("注册成功",user);
    }

    @Override
    public Result getAllUsers(int pageNum, int pageSize, String orderBy,String likeName,String role) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy(orderBy);
        List<User> list = userMapper.getUserByLikeNameAndRole(likeName,role);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo);
    }

    @Override
    public User selectByusername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public Result login(User user){
        User dbUser = selectByusername(user.getUsername());
        if (dbUser == null){
            return Result.error("账号或密码错误");
        }
        if (!user.getPassword().equals(dbUser.getPassword())){
            return Result.error("账号或密码错误");
        }
        String token = jwtUtil.createToken(dbUser);
        System.out.println("token:"+token);
        Map<String, Object> data = getUserInfo(token);
        System.out.println("data:"+data);
        data.put("token", token);
        return Result.success(data);
    }

    @Override
    public Result update(User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());
        dbUser.setNickname(user.getNickname());
        dbUser.setEmail(user.getEmail());
        dbUser.setSignature(user.getSignature());
        dbUser.setAvatar(user.getAvatar());
        dbUser.setRole(user.getRole());

        userMapper.update(dbUser);
        String token = jwtUtil.createToken(dbUser);
        Map<String, Object> data = getUserInfo(token);
        data.put("token", token);
        return Result.success("更新成功", data);
    }


    public Map<String, Object> getUserInfo(String token){
        User loginUser = null;
        try {
            loginUser = jwtUtil.parseToken(token,User.class);

        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("loginUser:"+loginUser);
        if (loginUser != null){
            Map<String, Object> data = new HashMap<>();
            data.put("uid", loginUser.getUid());
            data.put("username", loginUser.getUsername());
            data.put("nickname", loginUser.getNickname());
            data.put("email", loginUser.getEmail());
            data.put("signature", loginUser.getSignature());
            data.put("avatar", loginUser.getAvatar());
            data.put("role", loginUser.getRole());
            return data;
        }
        return null;
    }

    @Override
    public void addReadHistory(int bid, int uid) {
        userMapper.insertReadHistory(bid,uid);
    }

    @Override
    public Result updatePwd(UserVo userVo) {
        userMapper.updatePwd(userVo.getUsername(), userVo.getPassword());
        return Result.successMsg("更新成功");
    }

}
