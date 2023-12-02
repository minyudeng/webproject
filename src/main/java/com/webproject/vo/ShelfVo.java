package com.webproject.vo;

import com.webproject.entity.Shelf;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class ShelfVo {
    private Shelf shelf;
    private List<BookVo.BookSimpleShow> list;

}
