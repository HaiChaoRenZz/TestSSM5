package com.file.filetest.service;

import com.file.filetest.dao.UserFileDao;
import com.file.filetest.pojo.MyFIle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserFileServiceImpli   implements  UserFileService{

    @Autowired
    UserFileDao userFileDao;


    @Override
    public List<MyFIle> selectMyFile(Integer uid) {
        return userFileDao.selectFile(uid);
    }

    @Override
    public Integer save(MyFIle myFIle) {
        myFIle.setDowncount(0);
        myFIle.setUpLoadTime(new Date());
        return userFileDao.save(myFIle);
    }
}
