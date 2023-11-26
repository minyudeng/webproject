package com.webproject.mapper;

import com.webproject.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user(username,password,nickname,email,role)" +
            "values(#{username},#{password},#{nickname},#{email},#{role})")
    void insert(User user);

    User selectByUsername(String username);

    User selectByUid(int uid);

    @Update("UPDATE user " +
            "SET password = #{password}, " +
            "nickname = #{nickname}, " +
            "email = #{email}, " +
            "signature = #{signature}, " +
            "avatar = #{avatar}, " +
            "role = #{role} " +
            "WHERE username = #{username};")
    void update(User user);
    @Select("select * from user where (username like CONCAT('%', #{likename}, '%') or " +
            "nickname like CONCAT('%', #{likename}, '%') or " +
            "email like concat('%', #{likename}, '%')) and " +
            "role like concat('%', #{role}, '%')")
    List<User> getUserByLikeName(String likename,String role);

    @Update("update user set role = #{role} where uid = #{uid}")
    void setRole(String role, int uid);


    //阅读历史
    void insertReadHistory(int bid, int uid);
}
