package com.file.filetest.service;

import com.file.filetest.dao.UserDao;
import com.file.filetest.pojo.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyServiceImpl implements  MyUserService {
    @Autowired
    UserDao userDao;

    @Override
    public MyUser login(MyUser myUser) {
        return  userDao.login(myUser);
    }



}
