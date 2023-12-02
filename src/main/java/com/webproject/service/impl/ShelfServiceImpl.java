package com.webproject.service.impl;

import com.webproject.entity.Book;
import com.webproject.entity.Shelf;
import com.webproject.mapper.BookMapper;
import com.webproject.mapper.ShelfMapper;
import com.webproject.service.BookService;
import com.webproject.service.ShelfService;
import com.webproject.utils.Result;
import com.webproject.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelfServiceImpl implements ShelfService {
    @Autowired
    private ShelfMapper shelfMapper;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookVo.BookSimpleShow> getCollectionBooks(int uid) {
        return bookMapper.getBidByUid(uid)
                .stream()
                .map(bookService :: getBookSimpleShow)
                .toList();
    }

    @Override
    public Result addShelf(int uid, String shelfName, String intro) {
        if (isShelfNameExist(uid, shelfName)){
            return Result.error("书架名已经存在");
        }
        shelfMapper.insertShelf(uid, shelfName, intro);
        return Result.successMsg("创建成功");
    }

    @Override
    public Result updateShelf(int shelfId, String shelfName, String intro) {
        shelfMapper.updateShelf(shelfId, shelfName, intro);
        return Result.successMsg("更新成功");
    }

    @Override
    public List<Shelf> getShelfList(int uid) {
        return shelfMapper.selectSelfShelf(-1,uid,null);
    }

    @Override
    public boolean isShelfNameExist(int uid, String shelfName) {
        return shelfMapper.selectSelfShelf(-1,uid,shelfName).size() != 0;
    }

    @Override
    public List<BookVo.BookSimpleShow> getBooksByShelfId(int shelfId) {
        List<Integer> bidList = shelfMapper.selectBooksId(shelfId);
        List<BookVo.BookSimpleShow> retList = bidList
                .stream()
                .map(bookService :: getBookSimpleShow)
                .toList();
//                .collect(Collectors.toList());
        return retList;
    }
}
