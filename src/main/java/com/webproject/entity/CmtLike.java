package com.webproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cmt_like")
public class CmtLike {
    private int cid;
    private int uid;
    private int bid;
}
