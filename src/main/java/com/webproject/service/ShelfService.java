package com.webproject.service;

import com.webproject.entity.Shelf;
import com.webproject.mapper.ShelfMapper;
import com.webproject.utils.Result;
import com.webproject.vo.BookVo;

import java.util.List;
public interface ShelfService {
    List<BookVo.BookSimpleShow> getCollectionBooks(int uid);
    //part shelf
    Result addShelf(int uid, String shelfName,String intro);
    Result delShelf(int shelfId);
    Result updateShelf(int shelfId, String shelfName,String intro);
    List<Shelf> getShelfList(int uid);
    boolean isShelfNameExist(int uid,String shelfName);
    //part bookShelf
    Result addBookShelf(int shelfId, List<Integer> bidList);
    Result delBookShelf(List<Integer> bidList,int shelfId,int uid);
    List<BookVo.BookSimpleShow> getBooksByShelfId(int shelfId);

    //part userShelf

}
