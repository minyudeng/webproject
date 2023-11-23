package com.webproject.service;

import com.webproject.utils.Result;

import java.util.List;
import java.util.Map;

public interface ApplyService {
    Result addApply(int uid, String aname, String message);
    boolean setAudit(int audit, int uid);
    boolean isEmptyApply(int uid);
    List<Map<String,Object>> getApply(String likenam, String audit);
}
