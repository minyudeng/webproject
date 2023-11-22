package com.webproject.mapper;

import com.webproject.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select * from comment where bid = #{bid}")
    List<Comment> getAllCommentByBid(int bid);
}
