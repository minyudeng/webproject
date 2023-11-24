package com.webproject.service;

import com.webproject.entity.Comment;
import com.webproject.utils.Result;
import com.webproject.vo.CommentVo;


public interface CommentService {
    CommentVo.Cmt getFirstCmt(int bid);
    Result addCmt(Comment comment);
}
