package com.webproject.controller;

import com.webproject.entity.Shelf;
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
    Result addShelf(int uid, String shelfName, String intro,int show) {
        return shelfService.addShelf(uid, shelfName, intro,show);
    }

    @DeleteMapping("/shelf/del")
    Result delShelf(@RequestParam int shelfId){
        return shelfService.delShelf(shelfId);
    }

    @PutMapping("/shelf/put")
    Result updateShelf(Shelf shelf) {
        System.out.println(shelf);
        return shelfService.updateShelf(shelf.getShelfId(), shelf.getShelfName(), shelf.getIntro(), shelf.getShow());
    }

    @GetMapping("/shelf/get")
    Result getShelfByUid(@RequestParam(required = false,defaultValue = "-1") int uid,
                         @RequestParam(required = false,defaultValue = "-1") int show,
                         @RequestParam(required = false,defaultValue = "-1") int userId) {
        return Result.success(shelfService.getShelfList(uid,show,userId));
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
    @PutMapping("/shelf/collection")
    Result collectionShelf(int shelfId,int uid){
        return shelfService.collectionShelf(shelfId, uid);
    }
    @GetMapping("/book-shelf/get")
    Result getUserShelf(@RequestParam(required = false,defaultValue = "-1") int shelfId,
                        @RequestParam(required = false,defaultValue = "-1") int uid){
        return Result.success(shelfService.getUserShelf(shelfId, uid));
    }
}
