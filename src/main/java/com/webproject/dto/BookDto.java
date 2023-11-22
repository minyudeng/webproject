package com.webproject.dto;

import com.webproject.entity.Type;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class BookDto {
    @Data
    public class AddBook{
        private int aid;
        private String bname;
        private int uid;
        private String intro;
        private MultipartFile cover;
        private List<Integer> typeId;
    }
    @Data
    public static class BooksForAuthor{
        private int uid;
        private int bid;
        private String bname;
        private int aid;
        private String intro;
        private String cover;
        private String status;
        private String updateTime;
        private List<Type> types;
        private int collection;

        public BooksForAuthor(int uid, int bid, String bname, int aid, String intro, String cover, String status, String updateTime, List<Type> types, int collection) {
            this.uid = uid;
            this.bid = bid;
            this.bname = bname;
            this.aid = aid;
            this.intro = intro;
            this.cover = cover;
            this.status = status;
            this.updateTime = updateTime;
            this.types = types;
            this.collection = collection;
        }
    }
    @Data
    public class UpdateBookDto{
        private int aid;
        private String bname;
        private String intro;
        private List<Integer> typeId;
        private int bid;
    }
}
