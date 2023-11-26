package com.webproject.service.impl;

import com.webproject.mapper.BookMapper;
import com.webproject.mapper.CmtLikeMapper;
import com.webproject.mapper.CommentMapper;
import com.webproject.service.CmtLikeService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CmtLikeServiceImpl implements CmtLikeService {
    @Autowired
    private CmtLikeMapper cmtLikeMapper;
    @Autowired
    private CommentMapper commentMapper;
    public boolean isLikeExist(int cid, int uid){
        if (cmtLikeMapper.selectByCid(cid, uid) == null){
            return false;
        }
        return true;
    }
    @Override
    @Transactional
    public Result likeCmt(int cid, int uid) {
        if (isLikeExist(cid,uid)){
            try {
                cmtLikeMapper.delByCid(cid);
                commentMapper.reduceCmtLike(cid);
            }catch (Exception e){
                throw new RuntimeException("取消点赞失败");
            }
            return Result.successMsg("取消成功");
        }
        try {
            cmtLikeMapper.addCmtLike(cid,uid);
            commentMapper.addCmtLike(cid);
        }catch (Exception e){
            throw new RuntimeException("点赞失败");
        }
        return Result.successMsg("点赞成功");
    }

    @Override
    public Integer getLikeNum(int cid) {
//        return cmtLikeMapper.selectLikeNumber(cid);
        return null;
    }
}
