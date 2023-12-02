package com.webproject.controller;

import com.webproject.service.ShelfService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShelfController {
    @Autowired
    private ShelfService shelfService;
    @GetMapping("/shelf/get/collection")
    Result getCol(@RequestParam int uid){
        return Result.success(shelfService.getCollectionBooks(uid));
    }
    //part: shelf
    @PostMapping("/shelf/add")
    Result addShelf(int uid, String shelfName,String intro){
        return shelfService.addShelf(uid, shelfName, intro);
    }
    @PutMapping("/shelf/put")
    Result updateShelf( int shelfId, String shelfName,String intro){
        return shelfService.updateShelf(shelfId, shelfName, intro);
    }
    @GetMapping("/shelf/get")
    Result getShelfByUid(@RequestParam int uid){
        return Result.success(shelfService.getShelfList(uid));
    }

    //part: bookShelf
    @GetMapping("/book-shelf/get/{shelfId}")
    Result getShelfBooksByShelfId(@PathVariable int shelfId){
        return Result.success(shelfService.getBooksByShelfId(shelfId));
    }
    //part: userShelf


}
