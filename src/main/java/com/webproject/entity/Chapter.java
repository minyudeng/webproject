package com.webproject.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Chapter {
    private int bid;
    @Column(name = "chapter_id")
    private int chapterId;
    private String title;
    private String content;
    private String status;
    private LocalDateTime time;
    private String formatTime;
}
