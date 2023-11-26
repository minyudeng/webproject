package com.webproject.controller;

import com.webproject.entity.Comment;
import com.webproject.service.CommentService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/comment/get/{bid}")
    public Result getComment(@PathVariable int bid,@RequestParam int likeUid){
        return Result.success(commentService.getCmts(bid,likeUid));
    }
    @PostMapping("/comment/add")
    public Result addBookComment(Comment comment){
        return commentService.addCmt(comment);
    }
}
