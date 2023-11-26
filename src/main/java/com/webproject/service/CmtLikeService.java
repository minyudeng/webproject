package com.webproject.service;

import com.webproject.utils.Result;

public interface CmtLikeService {
    Result likeCmt(int cid, int uid);
    Integer getLikeNum(int cid);
    boolean isLikeExist(int cid, int uid);
}
