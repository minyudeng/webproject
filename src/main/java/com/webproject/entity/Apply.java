package com.webproject.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Apply {
    private int aplid;
    private int uid;
    private String aname;
    private String message;
    private LocalDateTime time;
    private int audit;

}
