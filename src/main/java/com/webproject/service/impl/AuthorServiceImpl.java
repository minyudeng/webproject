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
    @Override
    public Author selectByAname(String aname) {
        return authorMapper.selectByAname(aname);
    }

    @Override
    public Author selectByAid(int aid) {
        return authorMapper.selectByAid(aid);
    }

    @Override
    public boolean addAuthor(int uid, String aname) {
        try {
            authorMapper.addAuthor(uid, aname);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
