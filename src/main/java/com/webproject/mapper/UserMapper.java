package com.webproject.mapper;

import com.github.pagehelper.Page;
import com.webproject.entity.ReadHistory;
import com.webproject.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getUserByLikeNameAndRole(String likename,String role);

    User selectByUsername(String username);

    User selectByUid(int uid);

    @Insert("insert into user(username,password,nickname,email,role)" +
            "values(#{username},#{password},#{nickname},#{email},#{role})")
    void insert(User user);

    @Update("UPDATE user " +
            "SET password = #{password}, " +
            "nickname = #{nickname}, " +
            "email = #{email}, " +
            "signature = #{signature}, " +
            "avatar = #{avatar}, " +
            "role = #{role} " +
            "WHERE username = #{username};")
    void update(User user);


    @Update("update user set role = #{role} where uid = #{uid}")
    void setRole(String role, int uid);


    //阅读历史
    void insertReadHistory(int bid, int uid,int chapterId);
    void updateReadHistory(int bid, int uid,int chapterId);
    ReadHistory getReadHistory(int bid, int uid);


    void updatePwd(String username,String password);

}
