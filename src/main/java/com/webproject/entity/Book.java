package com.webproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import jakarta.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class Book {
    private int bid;
    private  String bname;
    private int aid;
    private String intro;
    private String cover;
    private double rating;
    private int numOfCollection;
    private String status;
//    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @TableField(exist = false)
    private List<Type> types;
    @TableField(exist = false)
    private List<Collection> collections;
}
