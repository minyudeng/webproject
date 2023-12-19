package com.webproject.service;

import com.webproject.entity.Chapter;
import com.webproject.utils.Result;
import com.webproject.vo.ChapterVo;

import java.util.List;

public interface ChapterService {
    Result insertChapter(Chapter chapter);

    Result updateChapter(Chapter chapter);

    List<Chapter> getChapters(int bid,String status);
    Chapter getChapter(int bid, int chapterId);
    ChapterVo getChapterDetail(int bid, int chapterId);
}
