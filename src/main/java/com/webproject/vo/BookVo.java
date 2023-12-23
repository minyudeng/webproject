package com.webproject.vo;

import com.webproject.entity.Type;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class BookVo {
    @Builder
    @Data
    public static class BookDetail{
        private int bid;
        private String bname;
        private String aname;
        private String cover;
        private String intro;
        private String updateTime;
        private List<Type> typeList;
        private int numOfCollection;
        private String status;
        private double rating;
        private boolean isCollection;
    }
    @Builder
    @Data
    public static class BookSimpleShow{
        private int bid;
        private String bname;
        private String cover;
        private String status;
    }
    @Builder
    @Data
    public static class BookHistoryVo{
        private int bid;
        private String bname;
        private String cover;
        private String intro;
        private String status;
        private String time;
    }
}
