package com.webproject.vo;

import com.webproject.entity.Type;
import lombok.Data;

import java.util.List;

public class BookVo {
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

        public BookDetail(String bname, String aname, String cover, String intro, String updateTime, List<Type> typeList, int numOfCollection, String status) {
            this.bname = bname;
            this.aname = aname;
            this.cover = cover;
            this.intro = intro;
            this.updateTime = updateTime;
            this.typeList = typeList;
            this.numOfCollection = numOfCollection;
            this.status = status;
        }
    }
}
