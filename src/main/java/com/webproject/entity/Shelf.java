package com.webproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Shelf {
    private int shelfId;
    private int uid;
    private String shelfName;
    private String intro;
    @Column(name = "collection_num")
    private String numOfCollection;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @TableField(exist = false)
    private String time;
}
