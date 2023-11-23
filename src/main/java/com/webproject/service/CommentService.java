package com.webproject.service;

import com.webproject.vo.CommentVo;


public interface CommentService {
    CommentVo.FirstCmt getFirstCmt(int bid);
}
