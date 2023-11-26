package com.webproject.service.impl;

import com.webproject.entity.Subcmt;
import com.webproject.entity.User;
import com.webproject.mapper.SubcmtMapper;
import com.webproject.mapper.UserMapper;
import com.webproject.service.SubcmtService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.webproject.utils.DateFormatUtil.formatTo;

@Service
public class SubcmtServiceImpl implements SubcmtService {
    @Autowired
    private SubcmtMapper subcmtMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Subcmt> getAllSubcmtByCid(int cid, int likeUId) {
        List<Subcmt> list = subcmtMapper.selectSubcmtByCId(cid);
        list.forEach(subcmt -> {
            User user = userMapper.selectByUid(subcmt.getUid());
            subcmt.setAvatar(user.getAvatar());
            subcmt.setUsername(user.getUsername());
            subcmt.setFormatTime(formatTo(subcmt.getTime(),"yyyy-MM-dd HH:mm:ss"));
            if (subcmtMapper.selectSubmtLikeByCidAndUid(subcmt.getSubcmtId(),likeUId) == null){
                subcmt.setLiked(false);
            }else {
                subcmt.setLiked(true);
            }
        });
        return list;
    }

    @Override
    public Result addSubcmt(Subcmt subcmt) {
        subcmtMapper.insertSubcmt(subcmt.getCid(),subcmt.getUid(),subcmt.getContent());
        return Result.successMsg("评论成功");
    }
    @Transactional
    @Override
    public Result likeSubCmt(int subcmtId, int uid) {
        if (subcmtMapper.selectSubmtLikeByCidAndUid(subcmtId, uid) != null){
            try {
                subcmtMapper.delSubCmtLike(subcmtId, uid);
                subcmtMapper.reduceSubCmtLike(subcmtId);
            }catch (Exception e){
                throw new RuntimeException("取消点赞失败");
            }
            return Result.successMsg("取消点赞成功");
        }else {
            try {
                subcmtMapper.addSubcmtLike(subcmtId, uid);
                subcmtMapper.updateSubcmtLike(subcmtId);
            }catch (Exception e){
                throw new RuntimeException("点赞成功");
            }
            return Result.successMsg("点赞成功");
        }
    }
}
