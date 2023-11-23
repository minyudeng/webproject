package com.webproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import jakarta.persistence.Column;
import java.time.LocalDateTime;
@Data
public class Comment {
    private int cid;
    private int uid;
    private int bid;
    private String content;
    private double rating;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @TableField(exist = false)
    private String time;
}

