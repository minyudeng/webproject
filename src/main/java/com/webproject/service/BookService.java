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
    List<Type> getAllType();
    Book getBookByBid(int bid);
    Result updateBookCover(String cover,int bid);
    Result updateBookStatus(String status,int bid);
    Result getBooks(int pageNum, int pageSize, String orderBy, String bname, int uid);
    BookVo.BookDetail getBookDetail(int bid,int uid);
    Result collectionBook(int bid,int uid);
    boolean isCollection(int uid, int bid);
}
