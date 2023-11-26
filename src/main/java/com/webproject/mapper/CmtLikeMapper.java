package com.webproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webproject.entity.CmtLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmtLikeMapper extends BaseMapper<CmtLike> {
    void addCmtLike(int cid,int uid);
    void delByCid(int cid);
    CmtLike selectByCid(int cid,int uid);
}
