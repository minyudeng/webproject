package com.webproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Subcmt {
    @Column(name = "subcmt_id")
    private int subcmtId;
    private int cid;
    private int uid;
    private String content;
    @Column(name = "time")
    private LocalDateTime time;
    @TableField(exist = false)//
    private String formatTime;
    private int like;

    @TableField(exist = false)
    private String avatar;
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private boolean liked;
}
