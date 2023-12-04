package com.webproject.controller;

import com.webproject.service.ShelfService;
import com.webproject.utils.Result;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShelfController {
    @Autowired
    private ShelfService shelfService;

    @GetMapping("/shelf/get/collection")
    Result getCol(@RequestParam int uid) {
        return Result.success(shelfService.getCollectionBooks(uid));
    }

    //part: shelf
    @PostMapping("/shelf/add")
    Result addShelf(int uid, String shelfName, String intro) {
        return shelfService.addShelf(uid, shelfName, intro);
    }

    @DeleteMapping("/shelf/del")
    Result delShelf(@RequestParam int shelfId){
        return shelfService.delShelf(shelfId);
    }

    @PutMapping("/shelf/put")
    Result updateShelf(int shelfId, String shelfName, String intro) {
        return shelfService.updateShelf(shelfId, shelfName, intro);
    }

    @GetMapping("/shelf/get")
    Result getShelfByUid(@RequestParam int uid) {
        return Result.success(shelfService.getShelfList(uid));
    }

    //part: bookShelf
    @Data
    class AddBookShelf{
        int shelfId;
        List<Integer> bidList;
    }
    @PostMapping("/book-shelf/add")
    Result addBookShelf(AddBookShelf addBookShelf){
        return shelfService.addBookShelf(addBookShelf.getShelfId(), addBookShelf.getBidList());
    }
    @GetMapping("/book-shelf/get/{shelfId}")
    Result getShelfBooksByShelfId(@PathVariable int shelfId) {
        return Result.success(shelfService.getBooksByShelfId(shelfId));
    }

    //    @DeleteMapping("/book-shelf/del")
//    Result delBookShelf(@RequestParam int shelfId,
//                        @RequestParam int uid,
//                        @RequestParam List<Integer> bidList)
//                        {
//        return shelfService.delBookShelf(bidList,shelfId,uid);
//    }
    @Data
    class delObj{
        int shelfId;
        int uid;
        List<Integer> bidList;
    }
    @PostMapping("/book-shelf/del")
    Result delBookShelf(delObj delObj) {
        return shelfService.delBookShelf(delObj.getBidList(), delObj.getShelfId(), delObj.getUid());
    }
    //part: userShelf


}
