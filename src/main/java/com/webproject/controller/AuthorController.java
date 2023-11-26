package com.webproject.controller;

import com.webproject.service.AuthorService;
import com.webproject.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthorController {
    @Autowired
    AuthorService authorService;

}
