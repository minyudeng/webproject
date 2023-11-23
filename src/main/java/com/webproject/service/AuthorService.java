package com.webproject.service;

import com.webproject.entity.Author;

public interface AuthorService {

    Author selectByAname(String aname);
    Author selectByAid(int aid);
    boolean addAuthor(int uid, String aname);
}
