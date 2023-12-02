package com.webproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@TableName("user_shelf")
public class UserShelf {
    @Column(name = "shelf_id")
    private int shelfId;
    private int uid;
}
