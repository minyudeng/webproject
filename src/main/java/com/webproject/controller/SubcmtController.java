package com.webproject.controller;

import com.webproject.entity.Subcmt;
import com.webproject.service.SubcmtService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubcmtController {
    @Autowired
    private SubcmtService subcmtService;

    @PostMapping("/subcmt/add")
    public Result addSubcmt(Subcmt subcmt){
        return subcmtService.addSubcmt(subcmt);
    }

    @GetMapping("/subcmt/get/{cid}")
    public Result getSubcmtsByCid(@PathVariable int cid, @RequestParam int likeUId){
        List<Subcmt> subcmts = subcmtService.getAllSubcmtByCid(cid,likeUId);
        return Result.success(subcmts);
    }
    @PutMapping("subcmt/like")
    public Result likeSubcmt(int subcmtId, int uid){
        return subcmtService.likeSubCmt(subcmtId, uid);
    }

}
