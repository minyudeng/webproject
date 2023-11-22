package com.webproject.service.impl;

import com.webproject.entity.Apply;
import com.webproject.mapper.ApplyMapper;
import com.webproject.mapper.AuthorMapper;
import com.webproject.mapper.UserMapper;
import com.webproject.service.ApplyService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyMapper applyMapper;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result addApply(int uid, String aname, String message) {
        if (!isEmptyApply(uid)){
            return Result.error("你已经提交过申请，请耐心等待！");
        }
        if (authorMapper.selectByAname(aname) != null){
            return Result.error("该笔名已存在！");
        }
        try {
            applyMapper.addApply(uid, aname, message,2);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("服务器错误！");
    }
    @Transactional
    @Override
    public boolean setAudit(int audit, int uid) {
        if (audit == 0){
            applyMapper.setAudit(0,uid);
            return true;
        }else if (audit == 1){
           try {
               applyMapper.setAudit(1,uid);
               Apply apply = applyMapper.selectApplyByUid(uid);
               authorMapper.addAuthor(uid,apply.getAname());
               userMapper.setRole("作者",uid);
           }catch (Exception e){
               throw new RuntimeException("申请失败");
           }
            return true;
        }
        return false;
    }


    @Override
    public boolean isEmptyApply(int uid) {
        Apply apply = applyMapper.selectApplyByUid(uid);
        if (apply == null || apply.getAudit() == 0){//没有这个申请或者这个申请被拒绝了
            return true;
        }

        return false;
    }

    @Override
    public List<Map<String,Object>> getApply(String likename, String audit) {
        List<Map<String,Object>> list = applyMapper.likeSelect(likename,audit);
        return list;
    }

}
