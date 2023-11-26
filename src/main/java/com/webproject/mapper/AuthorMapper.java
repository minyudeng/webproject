package com.webproject.mapper;

import com.webproject.entity.Author;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper

public interface AuthorMapper {

    @Select("select * from author where aname = #{aname}")
    Author selectByAname(String anname);

    @Select("select * from author where aid = #{aid}")
    Author selectByAid(int aid);
    void addAuthor(int uid, String aname);
    @Select("select * from author where uid = #{uid}")
    Author selectByUid(int uid);
}
