package com.webproject.service;

import com.webproject.dto.BookDto;
import com.webproject.entity.Book;
import com.webproject.entity.Type;
import com.webproject.utils.Result;
import com.webproject.vo.BookVo;

import java.util.List;
import java.util.Map;

public interface BookService {
    Result addBook(BookDto.AddBook book);
    Result updateBook(BookDto.UpdateBookDto updateBookDto);
    boolean isBookEmpty(String bname);
    List<BookDto.BooksForAuthor> getBookByAid(int aid);
    List<Type> getAllType();
    Book getBookByBid(int bid);
    Result updateBookCover(String cover,int bid);
    Result updateBookStatus(String status,int bid);
    List<Book> getLastFourBook();
    BookVo.BookDetail getBookDetail(int bid);
    Result collectionBook(int bid,int uid);
    boolean isCollection(int uid, int bid);
}
