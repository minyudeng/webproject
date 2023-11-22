package com.webproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import jakarta.persistence.Column;
@Data
@TableName("book_type")
public class BookType {
    private int bid;
    @Column(name = "type_id")
    private int typeId;
}
