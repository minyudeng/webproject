package com.webproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("read_history")
public class ReadHistory {
    private int bid;
    private int uid;
    @Column(name = "chapter_id")
    private int chapterId;
    private LocalDateTime time;
    @TableField(exist = false)
    private String formatTime;
}
