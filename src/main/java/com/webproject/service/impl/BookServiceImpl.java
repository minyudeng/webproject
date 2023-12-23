package com.webproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webproject.dto.BookDto;
import com.webproject.entity.*;
import com.webproject.mapper.*;
import com.webproject.service.BookService;
import com.webproject.service.FileService;
import com.webproject.utils.Result;
import com.webproject.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static com.webproject.utils.DateFormatUtil.formatTo;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private SubcmtMapper subcmtMapper;
    @Autowired
    private ChapterMapper chapterMapper;

    @Transactional
    @Override
    public Result addBook(BookDto.AddBook book) {
        if (!isBookEmpty(book.getBname())) {
            return Result.error("该书名已存在");
        }

        MultipartFile file = book.getCover();
        String originalFilename = file.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + fileType;
        String objectName = "images/bookCover/" + newFileName;

        String url = fileService.upload(objectName, file, null);

        try {
            Author author = authorMapper.selectByUid(book.getUid());

            bookMapper.addBook(book.getBname(), author.getAid(), book.getIntro(), url, "连载中");
            Book book1 = bookMapper.getOneBookByName(book.getBname());

            List<Integer> list = book.getTypeId();
            for (int i : list) {
                bookMapper.addBookToType(book1.getBid(), i);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加书籍失败");
        }
        return Result.success();
    }
    @Transactional
    @Override
    public Result delBookByBid(int bid) {
        //得到书的所有评论
        List<Comment> comments = commentMapper.getAllCommentByBid(bid);
        //得到书评的所有点赞

        //得到书的所有评论的子评论
        List<Subcmt> subcmts = new ArrayList<>();
        for (Comment comment : comments){
            subcmts.addAll(subcmtMapper.selectSubcmtByCId(comment.getCid()));
        }
        //得到所有子评论的点赞
        List<SubcmtLike> subcmtLikes = new ArrayList<>();
        for (SubcmtLike subcmtLike : subcmtLikes){
            subcmtLikes.add(subcmtMapper.selectSubmtLikeByCidAndUid(subcmtLike.getSubcmtId(),-1));
        }
        try {
            //先删sub书评的点赞
            subcmtLikes.forEach(subcmtLike -> {
                subcmtMapper.delSubCmtLike(subcmtLike.getSubcmtId(),-1);
            });
            //删除sub书评
                comments.forEach(comment -> {
                    subcmtMapper.delSubCmt(-1,comment.getCid(),-1);
                });
            //删除点赞

            //删除这本书在book_type表中的typeId
            bookMapper.delBookToType(bid);

        }catch (Exception e){
            throw new RuntimeException("删除失败");
        }
        return Result.successMsg("删除成功");
    }

    @Transactional
    @Override
    public Result updateBook(BookDto.UpdateBookDto newBook) {
        Book oldBook = bookMapper.getOneBookByBid(newBook.getBid());

        List<Integer> list = newBook.getTypeId();
        try {
            bookMapper.updateBook(newBook.getBname(), newBook.getIntro(), newBook.getBid());
            bookMapper.delBookToType(newBook.getBid());
            for (int i : list) {
                bookMapper.addBookToType(newBook.getBid(), i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新书籍失败");
        }
        return Result.success();
    }

    @Override
    public boolean isBookEmpty(String bname) {
        if (bookMapper.getOneBookByName(bname) == null) {
            return true;
        }
        return false;
    }

    @Override
    public List<BookDto.BooksForAuthor> getBookByAid(int uid) {
        Author author = authorMapper.selectByUid(uid);
        List<Book> books = bookMapper.getBooksByAid(author.getAid());
        List<BookDto.BooksForAuthor> list = new ArrayList<>();

        for (Book book : books) {
            //获得书的类型
            List<Integer> typeIds = bookMapper.getBookTypeId(book.getBid());
            List<Type> types = new ArrayList<>();
            for (Integer typeID : typeIds) {
                Type type = bookMapper.getTypeByTypeId(typeID);
                types.add(type);
            }
            //将时间格式化
            String formattedDateTime = formatTo(book.getUpdatedAt(), "yyyy-MM-dd HH:mm:ss");
            //获得收藏数
            int numOfCellection = bookMapper.getBookCollectionNum(book.getBid());
            BookDto.BooksForAuthor myBook = new BookDto.BooksForAuthor(uid, book.getBid(),
                    book.getBname(), author.getAid(), book.getIntro(), book.getCover(), book.getStatus(), formattedDateTime, types, numOfCellection);
            list.add(myBook);
        }
        return list;
    }

    //全部typelist
    public List<Type> getAllType() {
        return bookMapper.getAllType();
    }

    @Override
    public Book getBookByBid(int bid) {
        return bookMapper.getOneBookByBid(bid);
    }


    @Override
    public Result updateBookCover(String cover, int bid) {
        bookMapper.updateBookCover(cover, bid);
        return Result.success();
    }

    @Override
    public Result updateBookStatus(String status, int bid) {
        try {
            bookMapper.updateBookStatus(status, bid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新失败");
        }
        return Result.success();
    }

    @Override
    public Result getBooks(int pageNum, int pageSize, String orderBy, String bname,String status,int typeId, int uid) {
        List<BookVo.BookDetail> list = new ArrayList<>();
        List<Integer> ids;
        PageInfo pageInfo;

        PageHelper.startPage(pageNum, pageSize, orderBy);
        ids = bookMapper.getBooks(bname,status,typeId);
        pageInfo = new PageInfo<>(ids);

        ids.forEach(i -> {
            BookVo.BookDetail bookDetail = getBookDetail(i, uid);

            list.add(bookDetail);
        });
        pageInfo.setList(list);
        System.out.println("pageInfo=" + list);
        return Result.success(pageInfo);
    }

    @Override
    public BookVo.BookDetail getBookDetail(int bid, int uid) {
        Book book = bookMapper.getOneBookByBid(bid);
        Author author = authorMapper.selectByAid(book.getAid());
        List<Integer> typeIds = bookMapper.getBookTypeId(bid);
        List<Type> types = new ArrayList<>();
        for (Integer typeID : typeIds) {
            Type type = bookMapper.getTypeByTypeId(typeID);
            types.add(type);
        }
        String updateTime = formatTo(book.getUpdatedAt(), "yyyy-MM-dd HH:mm:ss");

        BookVo.BookDetail bookDetail = BookVo.BookDetail.builder()
                .bid(book.getBid())
                .bname(book.getBname())
                .aname(author.getAname())
                .cover(book.getCover())
                .intro(book.getIntro())
                .updateTime(updateTime)
                .typeList(types)
                .numOfCollection(book.getNumOfCollection())
                .status(book.getStatus())
                .rating(book.getRating())
                .isCollection(isCollection(uid, book.getBid()))
                .build();
        return bookDetail;
    }

    @Transactional
    @Override
    public Result collectionBook(int bid, int uid) {
        if (isCollection(uid, bid)) {
            try {
                bookMapper.delCollection(uid, bid);
                bookMapper.updateBookCollection(bookMapper.getBookCollectionNum(bid), bid);
                return Result.successMsg("取消成功");
            } catch (Exception e) {
                throw new RuntimeException("取消收藏失败");
            }
        }
        try {
            bookMapper.addCollection(uid, bid);
            bookMapper.updateBookCollection(bookMapper.getBookCollectionNum(bid), bid);
        } catch (Exception e) {
            throw new RuntimeException("收藏失败");
        }
        return Result.successMsg("加入成功");
    }

    @Override
    public boolean isCollection(int uid, int bid) {
        if (bid == -1) {
            return false;
        }
        List<Integer> books = bookMapper.getBidByUid(uid);
        for (int i : books) {
            if (i == bid) {
                return true;
            }
        }
        return false;
    }

    @Override
    public BookVo.BookSimpleShow getBookSimpleShow(int bid) {
        Book book = bookMapper.getOneBookByBid(bid);
        return BookVo.BookSimpleShow.builder()
                .bid(bid)
                .bname(book.getBname())
                .status(book.getStatus())
                .cover(book.getCover())
                .build();
    }

    @Override
    public List<BookVo.BookHistoryVo> getHistoryBooks(int uid) {
        List<BookVo.BookHistoryVo> ret = new ArrayList<>();
        List<ReadHistory> list = bookMapper.getHistoryBooks(uid);
        list.forEach(  i->{
            Book book = bookMapper.getOneBookByBid(i.getBid());
            Chapter chapter = chapterMapper.getChapter(i.getBid(),i.getChapterId(),"");
            if (chapter == null){
                chapter = new Chapter();
                chapter.setTitle("");
            }
            BookVo.BookHistoryVo vo = BookVo.BookHistoryVo.builder()
                    .bid(i.getBid())
                    .bname(book.getBname())
                    .chapterId(i.getChapterId())
                    .chapter(chapter.getTitle())
                    .cover(book.getCover())
                    .intro(book.getIntro())
                    .status(book.getStatus())
                    .time(formatTo(i.getTime(),"yyyy-MM-dd HH:mm:ss"))
                    .build();
            ret.add(vo);
        });
        return ret;
    }


}
