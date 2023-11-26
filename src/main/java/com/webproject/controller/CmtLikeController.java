package com.webproject.controller;

import com.webproject.service.CmtLikeService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CmtLikeController {
    @Autowired
    private CmtLikeService cmtLikeService;
    @PostMapping("/cmt-like/like")
    public Result addCmtLike(int cid,int uid){
        return cmtLikeService.likeCmt(cid,uid);
    }
}
