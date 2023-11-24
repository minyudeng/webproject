package com.webproject.vo;

import com.webproject.entity.Comment;
import lombok.Data;

import java.util.List;

public class CommentVo {
    @Data
    public static class Cmt{
        private List<Comment> comments;
        private int number;
        public Cmt(List<Comment> comments, int number) {
            this.comments = comments;
            this.number = number;
        }
    }
}
