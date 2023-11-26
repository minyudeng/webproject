package com.webproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webproject.entity.Book;
import com.webproject.entity.Collection;
import com.webproject.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    //insert
    //增加书籍
    void addBook(String bname, int aid, String intro, String cover, String status);
    //delete
    //update
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
    //select
    //根据书名获得单个书籍,用于去重
    Book getOneBookByName(String bname);
    Book getOneBookByBid(int bid);
    List<Book> orderBooksByRate(String order,Integer limit);
    //获得aid的所有作品
    List<Book>  getBooksByAid(int aid);
    //获取最后四条
    List<Book> getLastFour();


    //collection
    // 获得书的收藏数目
    Integer getBookCollectionNum(int bid);
    //添加收藏
    void addCollection(int uid, int bid);
    //删除收藏
    void delCollection(int uid, int bid);
    //获得收藏的书籍id
    List<Integer> getBidByUid(int uid);

    //type
    //根据typeid获得type
    Type getTypeByTypeId(int typeId);

    //获得所有类型
    List<Type> getAllType();
    //获得书的类型id
    List<Integer> getBookTypeId(int bid);
    //将书籍与分类关联
    void addBookToType(int bid,int typeId);
    //删除书的所有类型，用于更新
    void delBookToType(int bid);

}
