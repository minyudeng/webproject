package com.webproject.service;

import com.webproject.entity.Comment;
import com.webproject.utils.Result;
import com.webproject.vo.CommentVo;


public interface CommentService {
    CommentVo.Cmt getCmts(int bid,int likeUid);
    Result addCmt(Comment comment);
}
