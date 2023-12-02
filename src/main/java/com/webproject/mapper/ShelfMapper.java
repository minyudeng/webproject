package com.webproject.mapper;

import com.webproject.entity.Shelf;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShelfMapper {
    //Shelf
    void insertShelf(int uid, String shelfName,String intro);
    void updateShelf(int shelfId, String shelfName,String intro);
    List<Shelf> selectSelfShelf(int shelfId,int uid,String shelfName);


    //Book_shelf
    void insertBookShelf(int shelfId,int bid);
    void delBookShelf(int shelfId, int bid);
    List<Integer> selectBooksId(int shelfId);

    //User_Shelf
    void insertUserShelf(int shelfId,int uid);
    void delUserShelf(int shelfId,int uid);
    Integer selectNumOfCollection(int shelfId);
}
