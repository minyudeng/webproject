package com.webproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webproject.entity.CmtLike;
import com.webproject.entity.SubcmtLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmtLikeMapper {
    //cmt
    void addCmtLike(int cid,int uid);
    void delCmtLikeByCid(int cid,int uid);
    CmtLike selectCmtLikeByCidAndUid(int cid,int uid);



}
