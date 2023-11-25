package com.webproject.mapper;

import com.webproject.entity.Book;
import com.webproject.entity.Collection;
import com.webproject.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {
    //根据书名获得单个书籍,用于去重
    Book getOneBookByName(String bname);
    Book getOneBookByBid(int bid);
    List<Book> orderBooksByRate(String order,Integer limit);
    //增加书籍
    void addBook(String bname, int aid, String intro, String cover, String status);
    void updateBook(String bname,String intro,int bid);
    @Update("update book " +
            "set cover = #{cover} " +
            "where bid = #{bid} ")
    void updateBookCover(String cover,int bid);
    @Update("update book " +
            "set status = #{status} " +
            "where bid = #{bid}")
    void updateBookStatus(String status,int bid);
    void updateRating(double rating,int bid);
    //获得aid的所有作品
    List<Book>  getBooksByAid(int aid);
    //获取最后四条
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
    @Select({"<script>",
            "SELECT bid, COUNT(bid) AS collection_count",
            "FROM collection",
            "GROUP BY bid",
            "ORDER BY collection_count #{order}",
            "<if test='limit != null and limit > 0'>",
            "LIMIT #{limit}",
            "</if>",
            "</script>"})
    List<Map<String,Integer>> orderBooksByCollection(String order, Integer limit);
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
