package com.webproject.controller;

import com.webproject.service.CommentService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/comment/get/{bid}")
    public Result getComment(@PathVariable int bid){
        return Result.success(commentService.getFirstCmt(bid));
    }
    @PostMapping("/comment/add1")
    public Result addBookComment(){
        return null;
    }
}
