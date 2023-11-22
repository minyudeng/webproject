package com.webproject.mapper;

import com.webproject.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> selectAll();

    @Insert("insert into user(username,password,nickname,email,role)" +
            "values(#{username},#{password},#{nickname},#{email},#{role})")
    void insert(User user);

    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);
    @Select("select * from user where uid = #{uid}")
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
}
