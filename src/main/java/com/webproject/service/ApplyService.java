package com.webproject.service;

import com.webproject.utils.Result;

import java.util.List;
import java.util.Map;

public interface ApplyService {
    public Result addApply(int uid, String aname, String message);
    public boolean setAudit(int audit, int uid);
    public boolean isEmptyApply(int uid);
    public List<Map<String,Object>> getApply(String likenam, String audit);
}
