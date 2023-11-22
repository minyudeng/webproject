package com.webproject.controller;

import com.webproject.entity.Book;
import com.webproject.entity.User;
import com.webproject.service.BookService;
import com.webproject.service.FileService;
import com.webproject.service.UserService;
import com.webproject.utils.JwtUtil;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @PostMapping("/avatarUpload")
    public Result avatarUpload(MultipartFile file, HttpServletRequest request) {
        String token = request.getHeader("Token");
        User loginUser = jwtUtil.parseToken(token,User.class);
        User dbUser = userService.selectByusername(loginUser.getUsername());
        String oldName = dbUser.getAvatar();
        String originalFilename = file.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName= UUID.randomUUID() + fileType;

        String objectName = "images/avatar/" + newFileName;
        String url = fileService.upload(objectName,file,oldName);
        dbUser.setAvatar(url);
        Result data = userService.update(dbUser);

        return data;
    }

    @PutMapping("/book/updatecover")
    public Result updateCover(MultipartFile file,int bid){
        Book book = bookService.getBookByBid(bid);

        String oldName = book.getCover();
        String originalFilename = file.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName= UUID.randomUUID() + fileType;

        String objectName = "images/bookCover/" + newFileName;
        String url = fileService.upload(objectName,file,oldName);

        return bookService.updateBookCover(url,bid);
    }
}