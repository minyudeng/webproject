package com.webproject.service;

import com.webproject.entity.Author;

public interface AuthorService {

    public Author selectByAname(String aname);
    public Author selectByAid(int aid);
    public boolean addAuthor(int uid, String aname);
}
