package com.webproject.controller;

import com.webproject.entity.Chapter;
import com.webproject.service.ChapterService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @PostMapping("/chapter/add")
    Result addChapter(Chapter chapter){
        return chapterService.insertChapter(chapter);
    }

    @PutMapping("/chapter/put")
    Result updateChapter(Chapter chapter){
        return chapterService.updateChapter(chapter);
    }

    @GetMapping("/chapter/get")
    Result getChapters(@RequestParam int bid, @RequestParam String status){
        return Result.success(chapterService.getChapters(bid, status));
    }
    @GetMapping("/chapter/detail")
    Result getChapter(@RequestParam int bid, @RequestParam int chapterId){
        return Result.success(chapterService.getChapterDetail(bid, chapterId));
    }
}
