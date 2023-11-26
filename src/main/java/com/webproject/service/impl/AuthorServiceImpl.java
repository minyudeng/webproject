package com.webproject.service.impl;

import com.webproject.entity.Author;
import com.webproject.mapper.AuthorMapper;
import com.webproject.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorMapper authorMapper;

}
