package com.file.filetest.dao;

import com.file.filetest.pojo.MyFIle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserFileDao {
    //根据id查看用户文件列表
    List<MyFIle> selectFile( Integer uid);

    //保存用户文件信息
    public Integer save(MyFIle myFIle);
}
