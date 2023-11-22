package com.webproject.service;

import com.webproject.dto.BookDto;
import com.webproject.entity.Book;
import com.webproject.entity.Type;
import com.webproject.utils.Result;
import com.webproject.vo.BookVo;

import java.util.List;

public interface BookService {
    Result addBook(BookDto.AddBook book);
    Result updateBook(BookDto.UpdateBookDto updateBookDto);
    boolean isBookEmpty(String bname);
    List<BookDto.BooksForAuthor> getBookByAid(int aid);
    public List<Type> getAllType();
    public Book getBookByBid(int bid);
    public Result updateBookCover(String cover,int bid);
    public Result updateBookStatus(String status,int bid);
    public List<Book> getLastFourBook();
    public BookVo.BookDetail getBookDetail(int bid);
    public Result collectionBook(int bid,int uid);
    public boolean isCollection(int uid, int bid);
}
