package com.webproject.mapper;

import com.webproject.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select * from comment")
    List<Comment> getAllCmt();
    @Select("select * from comment where bid = #{bid}")
    List<Comment> getAllCommentByBid(int bid);
    @Insert("INSERT INTO comment(uid,bid,content,rating,`like`) " +
            "VALUES(#{uid},#{bid},#{content},#{rating},0)")
    void insertCmt(int uid, int bid, String content, double rating);
    void addCmtLike(int cid);
    void reduceCmtLike(int cid);

}
