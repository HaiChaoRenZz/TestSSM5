package com.file.filetest.dao;

import com.file.filetest.pojo.MyUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    MyUser login(MyUser myUser);
}
