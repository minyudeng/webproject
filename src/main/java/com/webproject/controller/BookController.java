package com.webproject.controller;

import com.webproject.dto.BookDto;
import com.webproject.entity.Book;
import com.webproject.entity.Type;
import com.webproject.service.BookService;
import com.webproject.utils.Result;
import com.webproject.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/book/add")
    public Result addBook(BookDto.AddBook body){
        System.out.println(body.toString());
        return bookService.addBook(body);
    }
    @GetMapping("/book/uid")
    public Result getBooksByUid(@RequestParam int uid){
        List<BookDto.BooksForAuthor> books = bookService.getBookByAid(uid);
        System.out.println(books.toString());
        return Result.success(books);
    }
    @PutMapping ("/book/update")
    public Result update(BookDto.UpdateBookDto bookDto){
        Result result = bookService.updateBook(bookDto);
        return result;
    }
    @PutMapping("/book/update/status")
    public Result updateStatus(String status,int bid){
        return bookService.updateBookStatus(status, bid);
    }
    @GetMapping("/book/get")
    public Result getBooks(@RequestParam(required = false, defaultValue = "1") int pageNum,
                           @RequestParam(required = false, defaultValue = "0") int pageSize,
                           @RequestParam(required = false, defaultValue = "bid asc") String orderBy,
                           @RequestParam(required = false,defaultValue = "") String bname){
        return bookService.getBooks(pageNum,pageSize,orderBy,bname);
    }

    @GetMapping("/book/{bid}")
    public Result getBookDetail(@PathVariable int bid){
        BookVo.BookDetail bookDetail = bookService.getBookDetail(bid);
        return Result.success(bookDetail);
    }
    @PutMapping("/book/collection")
    public Result collection(@RequestParam int bid,@RequestParam int uid){
        return bookService.collectionBook(bid,uid);
    }
    @GetMapping("/book/is-collection")
    public Result isCollection(@RequestParam int bid,@RequestParam int uid){
        boolean bool = bookService.isCollection(uid,bid);
        return Result.success(bool);
    }
    @GetMapping("/type/get")
    public Result getAllType(){
        List<Type> typeList = bookService.getAllType();
        return Result.success(typeList);
    }
}
