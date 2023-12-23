package com.webproject.service.impl;

import com.webproject.entity.Chapter;
import com.webproject.mapper.BookMapper;
import com.webproject.mapper.ChapterMapper;
import com.webproject.service.ChapterService;
import com.webproject.utils.Result;
import com.webproject.vo.ChapterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.webproject.utils.DateFormatUtil.formatTo;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Result insertChapter(Chapter chapter) {
        int latest = chapterMapper.getLatestChapterId(chapter.getBid());
        if (latest == -1){
            chapterMapper.insertChapter(chapter.getBid(),1, "未命名章节", "", "未发布");
        }else {
            chapterMapper.insertChapter(chapter.getBid(),latest + 1, "未命名章节", "", "未发布");
        }

        return Result.successMsg("创建成功");
    }

    @Override
    @Transactional
    public Result updateChapter(Chapter chapter) {
        LocalDateTime dateTime = LocalDateTime.now();
        try {
            chapterMapper.updateChapter(chapter.getBid(),chapter.getChapterId(), chapter.getTitle(),  chapter.getContent(), chapter.getStatus());
            bookMapper.updateBookTime(chapter.getBid(),dateTime);
        }catch (Exception e){
            throw new RuntimeException("更新失败");
        }
        return Result.successMsg("更新成功");
    }

    @Override
    public List<Chapter> getChapters(int bid,String status) {
        List<Chapter> chapters = chapterMapper.getChapters(bid,-1,status);
        chapters.forEach(chapter -> chapter.setFormatTime(formatTo(chapter.getTime(),"yyyy-MM-dd HH:mm:ss")));
        return chapters;
    }

    @Override
    public Chapter getChapter(int bid, int chapterId) {

        return chapterMapper.getChapter(bid,chapterId,"已发布");
    }

    @Override
    public ChapterVo getChapterDetail(int bid, int chapterId) {
        Chapter chapter = chapterMapper.getChapter(bid,chapterId,"已发布");

        return ChapterVo.builder()
                .title(chapter.getTitle())
                .content(chapter.getContent())
                .pre(chapterId != 1)
                .next(chapterMapper.getChapter(bid,chapterId + 1,"已发布") != null)
                .build();
    }
}
