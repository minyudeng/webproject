package com.webproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@TableName("book_shelf")
public class BookShelf {
    @Column(name = "shelf_id")
    private int shelfId;
    private int bid;
}
