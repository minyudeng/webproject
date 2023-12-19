package com.webproject.mapper;

import com.webproject.entity.Code;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeMapper {
    void insertCode(String username,String email, String code);
    void updateCode(String username,String email, String code);
    Code selectCode(String username);
}
