package com.file.filetest.service;

import com.file.filetest.pojo.MyFIle;

import java.util.List;

public interface UserFileService {
    List<MyFIle> selectMyFile(Integer uid);

    Integer save(MyFIle myFIle);
}
