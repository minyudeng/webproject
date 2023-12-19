package com.webproject.mapper;

import com.webproject.entity.Chapter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChapterMapper {
    void insertChapter(int bid,int chapterId,String title,String content,String status);


    void updateChapter(int bid,int chapterId,String title,String content,String status);

    List<Chapter> getChapters(int bid,int chapterId,String status);
    Integer getLatestChapterId(int bid);
    Chapter getChapter(int bid,int chapterId,String status);
}
