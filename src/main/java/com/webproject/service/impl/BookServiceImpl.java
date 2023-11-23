package com.webproject.service.impl;

import com.webproject.dto.BookDto;
import com.webproject.entity.Author;
import com.webproject.entity.Book;
import com.webproject.entity.Type;
import com.webproject.mapper.AuthorMapper;
import com.webproject.mapper.BookMapper;
import com.webproject.service.BookService;
import com.webproject.service.FileService;
import com.webproject.utils.Result;
import com.webproject.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.webproject.utils.DateFormatUtil.formatTo;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private AuthorMapper authorMapper;

    @Transactional
    @Override
    public Result addBook(BookDto.AddBook book) {
        if (!isBookEmpty(book.getBname())){
            return Result.error("该书名已存在");
        }

        MultipartFile file = book.getCover();
        String originalFilename = file.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName= UUID.randomUUID() + fileType;
        String objectName = "images/bookCover/" + newFileName;

        String url = fileService.upload(objectName,  file,null);

        try {
            Author author = authorMapper.selectByUid(book.getUid());

            bookMapper.addBook(book.getBname(), author.getAid(), book.getIntro(),url,"连载中");
            Book book1 = bookMapper.getOneBookByName(book.getBname());

            List<Integer> list = book.getTypeId();
            for (int i : list){
                bookMapper.addBookToType(book1.getBid(), i);
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("添加书籍失败");
        }
        return Result.success();
    }
    @Transactional
    @Override
    public Result updateBook(BookDto.UpdateBookDto newBook) {
        Book oldBook = bookMapper.getOneBookByBid(newBook.getBid());

        List<Integer> list = newBook.getTypeId();
        try {
            bookMapper.updateBook(newBook.getBname(),newBook.getIntro(),newBook.getBid());
            bookMapper.delBookToType(newBook.getBid());
            for(int i : list){
                bookMapper.addBookToType(newBook.getBid(), i);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新书籍失败");
        }
        return Result.success();
    }

    @Override
    public boolean isBookEmpty(String bname) {
        if (bookMapper.getOneBookByName(bname) == null){
            return true;
        }
        return false;
    }

    @Override
    public List<BookDto.BooksForAuthor> getBookByAid(int uid) {
        Author author = authorMapper.selectByUid(uid);
        List<Book> books = bookMapper.getBooksByAid(author.getAid());
        List<BookDto.BooksForAuthor> list = new ArrayList<>();

        for (Book book : books){
            //获得书的类型
            List<Integer> typeIds = bookMapper.getBookTypeId(book.getBid());
            List<Type> types = new ArrayList<>();
            for (Integer typeID : typeIds){
                Type type = bookMapper.getTypeByTypeId(typeID);
                types.add(type);
            }
            //将时间格式化
            String formattedDateTime = formatTo(book.getUpdatedAt(), "yyyy-MM-dd HH:mm:ss");
            //获得收藏数
            int numOfCellection = bookMapper.getBookCollection(book.getBid());
            BookDto.BooksForAuthor myBook = new BookDto.BooksForAuthor(uid, book.getBid(),
                    book.getBname(), author.getAid(), book.getIntro(), book.getCover(), book.getStatus(), formattedDateTime, types, numOfCellection);
            list.add(myBook);
        }
        return list;
    }
    //全部typelist
    public List<Type> getAllType(){
        return bookMapper.getAllType();
    }

    @Override
    public Book getBookByBid(int bid) {
        return bookMapper.getOneBookByBid(bid);
    }

    @Override
    public Result updateBookCover(String cover, int bid) {
        bookMapper.updateBookCover(cover,bid);
        return Result.success();
    }

    @Override
    public Result updateBookStatus(String status, int bid) {
        try {
            bookMapper.updateBookStatus(status,bid);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("更新失败");
        }
        return Result.success();
    }

    @Override
    public List<Book> getLastFourBook() {
        return bookMapper.getLastFour();
    }

    @Override
    public BookVo.BookDetail getBookDetail(int bid) {
        Book book = bookMapper.getOneBookByBid(bid);
        Author author = authorMapper.selectByAid(book.getAid());
        List<Integer> typeIds = bookMapper.getBookTypeId(bid);
        List<Type> types = new ArrayList<>();
        for (Integer typeID : typeIds){
            Type type = bookMapper.getTypeByTypeId(typeID);
            types.add(type);
        }
        String updateTime = formatTo(book.getUpdatedAt(),"yyyy-MM-dd HH:mm:ss");

        BookVo.BookDetail bookDetail = new BookVo.BookDetail(book.getBname(),author.getAname(),book.getCover(),
                book.getIntro(),updateTime,types,bookMapper.getBookCollection(bid),book.getStatus());
        return bookDetail;
    }

    @Override
    public Result collectionBook(int bid, int uid) {
        if (isCollection(uid,bid)){
            bookMapper.delCollection(uid,bid);
            return Result.successMsg("取消成功");
        }
        bookMapper.addCollection(uid,bid);
        return Result.successMsg("加入成功");
    }

    @Override
    public boolean isCollection(int uid, int bid) {
        List<Integer> books = bookMapper.getBidByUid(uid);
        for (int i : books){
            if (i == bid){
                return true;
            }
        }
        return false;
    }
}
