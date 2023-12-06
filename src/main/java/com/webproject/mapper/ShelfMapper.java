package com.webproject.mapper;

import com.webproject.entity.Shelf;
import com.webproject.entity.UserShelf;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShelfMapper {
    //Shelf
    void insertShelf(int uid, String shelfName,String intro,int show);
    void delShelf(int shelfId,int uid);
    void updateShelf(int shelfId, String shelfName,String intro,int show);
    void updateShelfCollection(int shelfId,int num);
    List<Shelf> selectShelf(int shelfId,int uid,String shelfName,int show);


    //Book_shelf
    void insertBookShelf(int shelfId,int bid);
    void delBookShelf(int shelfId, int bid);
    List<Integer> selectBooksId(int shelfId);

    //User_Shelf
    void insertUserShelf(int shelfId,int uid);
    void delUserShelf(int shelfId,int uid);
    Integer selectNumOfCollection(int shelfId);
    List<UserShelf> selectUserShelf(int shelfId,int uid);
}
