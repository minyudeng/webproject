package com.webproject.mapper;

import com.webproject.entity.CmtLike;
import com.webproject.entity.Subcmt;
import com.webproject.entity.SubcmtLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SubcmtMapper{
    //子评论
    //增加
    void insertSubcmt(int cid, int uid ,String content);
    //根据cid获得全部评论
    List<Subcmt> selectSubcmtByCId(int cid);
    //评论数量
    Integer selectSubcmtNumByCId(int cid);

    //删除cid下的所有评论
    void delSubCmt(int subcmtId,int cid,int uid);

    //点赞数
    void updateSubcmtLike(int subcmtId);
    void reduceSubCmtLike(int subcmtId);

    //点赞相关
    void addSubcmtLike(int subcmtId, int uid);
    void delSubCmtLike(int subcmtId,int uid);
    SubcmtLike selectSubmtLikeByCidAndUid(int subcmtId, int uid);

}
