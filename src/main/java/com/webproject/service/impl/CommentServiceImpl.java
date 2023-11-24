package com.webproject.service.impl;

import com.webproject.entity.Comment;
import com.webproject.entity.User;
import com.webproject.mapper.BookMapper;
import com.webproject.mapper.CommentMapper;
import com.webproject.mapper.UserMapper;
import com.webproject.service.BookService;
import com.webproject.service.CommentService;
import com.webproject.service.UserService;
import com.webproject.utils.Result;
import com.webproject.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.webproject.utils.DateFormatUtil.formatTo;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public CommentVo.Cmt getFirstCmt(int bid) {
        List<Comment> comments = commentMapper.getAllCommentByBid(bid);
        for (Comment comment : comments){
            comment.setTime(formatTo(comment.getCreatedAt(),"yyyy-MM-dd HH:mm:ss"));

            User user = userMapper.selectByUid(comment.getUid());
            comment.setAvatar(user.getAvatar());
            comment.setUsername(user.getUsername());
        }

        CommentVo.Cmt firstCmt = new CommentVo.Cmt(comments,comments.size());
        return firstCmt;
    }
    @Transactional
    @Override
    public Result addCmt(Comment comment) {
        try {
            commentMapper.insertCmt(comment.getUid(),comment.getBid(),comment.getContent(),comment.getRating());
            updateRating(comment.getBid());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新评分异常");
        }
        return Result.success("评论成功");
    }
    @Transactional
    public boolean updateRating(int bid){
        List<Comment> comments = commentMapper.getAllCmt();
        System.out.println("comments="+ comments);
        List<Double> list = comments.stream()
                .map(Comment::getRating)
                .collect(Collectors.toList());

        double average = list.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        try {
            bookMapper.updateRating(average, bid);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新评分异常");
        }
    }
}
