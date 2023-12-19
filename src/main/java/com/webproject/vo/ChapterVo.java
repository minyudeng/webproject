package com.webproject.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChapterVo {
    private String title;
    private String content;
    private boolean pre;
    private boolean next;
}
