package com.webproject.service.impl;

import com.webproject.entity.Comment;
import com.webproject.mapper.CommentMapper;
import com.webproject.service.CommentService;
import com.webproject.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.webproject.utils.DateFormatUtil.formatTo;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public CommentVo.FirstCmt getFirstCmt(int bid) {
        List<Comment> comments = commentMapper.getAllCommentByBid(bid);
        for (Comment comment : comments){
            comment.setTime(formatTo(comment.getCreatedAt(),"yyyy-MM-dd HH:mm:ss"));
        }

        CommentVo.FirstCmt firstCmt = new CommentVo.FirstCmt(comments,comments.size());
        return firstCmt;
    }
}
