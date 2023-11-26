package com.webproject.service;

import com.webproject.entity.Subcmt;
import com.webproject.utils.Result;

import java.util.List;

public interface SubcmtService {
    List<Subcmt> getAllSubcmtByCid(int cid,int likeId);
    Result addSubcmt(Subcmt subcmt);
    Result likeSubCmt(int subcmtId, int uid);
}
