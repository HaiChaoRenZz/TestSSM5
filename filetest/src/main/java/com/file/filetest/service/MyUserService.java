package com.file.filetest.service;

import com.file.filetest.pojo.MyUser;
import org.springframework.stereotype.Service;


public interface MyUserService {
    MyUser login(MyUser myUser);
}
