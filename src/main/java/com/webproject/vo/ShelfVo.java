package com.webproject.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.webproject.entity.Shelf;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Builder
@Data
public class ShelfVo {
    @Data
    @Builder
    public static class ShlefShowVo{
        private int shelfId;
        private int uid;
        private String avatar;
        private String nickname;
        private String shelfName;
        private String intro;
        private int show;
        private int collectionNum;
        private Boolean isCollection;
        private String time;
    }

}
