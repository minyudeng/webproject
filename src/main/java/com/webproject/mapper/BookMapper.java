package com.webproject.mapper;

import com.webproject.entity.Book;
import com.webproject.entity.Collection;
import com.webproject.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    //根据书名获得单个书籍,用于去重
    @Select("select * from book where bname = #{bname}")
    Book getOneBookByName(String bname);
    @Select("select * from book where bid = #{bid}")
    Book getOneBookByBid(int bid);

    //增加书籍
    @Insert("insert into book(bname,aid,intro,cover,status) " +
            "values(#{bname}, #{aid}, #{intro}, #{cover}, #{status})")
    void addBook(String bname, int aid, String intro, String cover, String status);

    @Update("update book " +
            "set bname = #{bname}, " +
            "intro = #{intro} " +
            "where bid = #{bid}")
    void updateBook(String bname,String intro,int bid);
    @Update("update book " +
            "set cover = #{cover} " +
            "where bid = #{bid} ")
    void updateBookCover(String cover,int bid);
    @Update("update book " +
            "set status = #{status} " +
            "where bid = #{bid}")
    void updateBookStatus(String status,int bid);
    //获得aid的所有作品
    @Select("select * from book where aid = #{aid}")
    List<Book>  getBooksByAid(int aid);
    //获取最后四条
    @Select("SELECT bid,bname,intro FROM book ORDER BY bid DESC LIMIT 4")
    List<Book> getLastFour();


    //collection
    @Select("SELECT COUNT(*) FROM collection WHERE bid = #{bid}")
    Integer getBookCollection(int bid);
    @Insert("insert into collection(uid,bid) " +
            "values(#{uid}, #{bid}) ")
    void addCollection(int uid, int bid);
    @Delete("DELETE FROM collection WHERE bid = #{bid} AND uid = #{uid}")
    void delCollection(int uid, int bid);
    @Select("select bid from collection where uid = #{uid}")
    List<Integer> getBidByUid(int uid);
    @Select("select uid from collection where bid = #{bid}")
    List<Integer> getUidByBid(int bid);
    //type
    //获得固定类型
    @Select("select * from type where type_id = #{typeId}")
    Type getTypeByTypeId(int typeId);

    //获得所有类型
    @Select("select * from type")
    List<Type> getAllType();
    @Select("select type_id from book_type where bid = #{bid}")
    List<Integer> getBookTypeId(int bid);
    //将书籍与分类关联
    @Insert("insert into book_type(bid, type_id) " +
            "values(#{bid}, #{typeId})")
    void addBookToType(int bid,int typeId);
    @Delete("delete from book_type where bid = #{bid}")
    void delBookToType(int bid);

}
