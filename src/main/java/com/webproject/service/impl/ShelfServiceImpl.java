package com.webproject.service.impl;

import com.webproject.entity.Book;
import com.webproject.entity.Shelf;
import com.webproject.entity.User;
import com.webproject.entity.UserShelf;
import com.webproject.mapper.BookMapper;
import com.webproject.mapper.ShelfMapper;
import com.webproject.mapper.UserMapper;
import com.webproject.service.BookService;
import com.webproject.service.ShelfService;
import com.webproject.utils.Result;
import com.webproject.vo.BookVo;
import com.webproject.vo.ShelfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.webproject.utils.DateFormatUtil.formatTo;

@Service
public class ShelfServiceImpl implements ShelfService {
    @Autowired
    private ShelfMapper shelfMapper;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<BookVo.BookSimpleShow> getCollectionBooks(int uid) {
        return bookMapper.getBidByUid(uid)
                .stream()
                .map(bookService :: getBookSimpleShow)
                .toList();
    }

    @Override
    public Result addShelf(int uid, String shelfName, String intro,int show) {
        if (isShelfNameExist(uid, shelfName)){
            return Result.error("书架名已经存在");
        }
        shelfMapper.insertShelf(uid, shelfName, intro,show);
        return Result.successMsg("创建成功");
    }
    @Transactional
    @Override
    public Result delShelf(int shelfId) {
        try {
            shelfMapper.delBookShelf(shelfId,-1);
            shelfMapper.delUserShelf(shelfId,-1);
            shelfMapper.delShelf(shelfId,-1);
            return Result.success();
        }catch (Exception e){
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public Result updateShelf(int shelfId, String shelfName, String intro,int show) {
        shelfMapper.updateShelf(shelfId, shelfName, intro,show);
        return Result.successMsg("更新成功");
    }

    @Override
    public List<ShelfVo.ShlefShowVo> getShelfList(int uid,int show,int userId) {
        List<ShelfVo.ShlefShowVo> ret = shelfMapper.selectShelf(-1,uid,null,show)
                .stream().map(shelf -> buildShlefShowVo(shelf,userId))
                .toList();

        return ret;
    }
    private ShelfVo.ShlefShowVo buildShlefShowVo(Shelf shelf,int uid){
        User user = userMapper.selectByUid(shelf.getUid());
        ShelfVo.ShlefShowVo ret = ShelfVo.ShlefShowVo.builder()
                .shelfId(shelf.getShelfId())
                .uid(shelf.getUid())
                .avatar(user.getAvatar())
                .nickname(user.getNickname())
                .shelfName(shelf.getShelfName())
                .intro(shelf.getIntro())
                .show(shelf.getShow())
                .collectionNum(shelf.getCollectionNum())
                .isCollection(isCollection(shelf.getShelfId(), uid))
                .time(formatTo(shelf.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"))
                .build();
        return ret;

    }

    @Override
    public boolean isShelfNameExist(int uid, String shelfName) {
        return shelfMapper.selectShelf(-1,uid,shelfName,-1).size() != 0;
    }

    @Override
    @Transactional
    public Result addBookShelf(int shelfId, List<Integer> bidList) {
        if (bidList == null || bidList.size() == 0){
            return Result.error("至少添加一本书");
        }
        try {
            bidList.forEach(i->shelfMapper.insertBookShelf(shelfId,i));
            return Result.successMsg("添加成功");
        }catch (Exception e){
            throw new RuntimeException("添加失败");
        }
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

    @Override
    public Boolean isCollection(int shelfId, int uid) {
        List<UserShelf> userShelfs = shelfMapper.selectUserShelf(shelfId, uid);
        System.out.println("shelfId"+shelfId+" uid="+uid);
        if (userShelfs.size() == 0){
            return false;
        }
        return true;
    }

    @Override
    public Result collectionShelf(int shelfId, int uid) {
        if (isCollection(shelfId, uid)){
            shelfMapper.delUserShelf(shelfId, uid);
            shelfMapper.updateShelfCollection(shelfId,shelfMapper.selectNumOfCollection(shelfId));
            return Result.successMsg("取消收藏成功");
        }else {
            shelfMapper.insertUserShelf(shelfId, uid);
            shelfMapper.updateShelfCollection(shelfId,shelfMapper.selectNumOfCollection(shelfId));
            return Result.successMsg("收藏成功");
        }
    }

    @Override
    public List<ShelfVo.ShlefShowVo> getUserShelf(int shelfId, int uid) {
        List<Integer> shelfIdList = shelfMapper.selectUserShelf(shelfId,uid)
                .stream()
                .map(userShelf -> userShelf.getShelfId())
                .toList();
        List<Shelf> shelfList = new ArrayList<>();
        shelfIdList.forEach(id ->{
            List<Shelf> shelfs = shelfMapper.selectShelf(id,-1,null,1);
            shelfList.addAll(shelfs);
        });

        List<ShelfVo.ShlefShowVo> shlefShowVos = shelfList
                .stream()
                .map(shelf -> buildShlefShowVo(shelf,-1))
                .toList();
        return shlefShowVos;
    }

    @Override
    public Result delBookShelf(List<Integer> bidList, int shelfId, int uid) {
        if (shelfId == 0){
            bidList.forEach(bid -> {
                bookService.collectionBook(bid,uid);
            });
        }
        bidList.forEach(i -> shelfMapper.delBookShelf(shelfId,i));
        return Result.successMsg("删除成功");
    }
}
