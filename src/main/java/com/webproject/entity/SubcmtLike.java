package com.webproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@TableName("subcmt_like")
public class SubcmtLike {
    @Column(name = "subcmt_id")
    private int subcmtId;
    private int uid;
}
