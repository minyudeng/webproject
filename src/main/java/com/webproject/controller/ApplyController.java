package com.webproject.controller;

import com.webproject.mapper.AuthorMapper;
import com.webproject.service.ApplyService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    @PostMapping("/apply/add")
    public Result addApply(@RequestBody Map<String,String> map){

        Result result = applyService.addApply(Integer.valueOf(map.get("uid")), map.get("aname"), map.get("message"));
        return result;
    }
    @GetMapping("/apply/get")
    public Result getApply(@RequestParam String likename,@RequestParam(required = false,defaultValue = "") String audit){
        List<Map<String,Object>> list;
        list = applyService.getApply(likename, audit);
        return Result.success("查询成功",list);
    }
    @PutMapping("/apply/update/audit")
    public Result uodateAudit(@RequestBody Map<String,String> map){
        if (applyService.setAudit(Integer.parseInt(map.get("audit")),Integer.parseInt(map.get("uid")))){
            return Result.success();
        }
        return Result.error();
    }

}
