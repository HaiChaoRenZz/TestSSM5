package com.file.filetest.Contoller;

import com.file.filetest.pojo.MyFIle;
import com.file.filetest.pojo.MyUser;
import com.file.filetest.service.UserFileService;
import org.apache.commons.io.FilenameUtils;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("file")
public class MyFileController {

    @Autowired
    UserFileService userFileService;

    //展示所有文件信息
    @GetMapping("findAll")
    public String findAll(HttpSession session, Model model){

        MyUser user =(MyUser) session.getAttribute("myuser");
        Integer uid=user.getId();

        List<MyFIle> myfiles=userFileService.selectMyFile(uid);
        myfiles.forEach(a-> System.out.println(a.toString()));


        model.addAttribute("myfiles",myfiles);

        return  "showfile";
    }


    //处理文件上传 并保存信息到数据库
    @PostMapping("myupfile")
    public String myupfile(MultipartFile upfile,HttpSession session) throws IOException {
        System.out.println("asdadwqdqdqwdqwdq");

        //获取用户id
        MyUser user =(MyUser) session.getAttribute("myuser");
        Integer uid=user.getId();


        //获取文件原始名称
        String oldFileName=upfile.getOriginalFilename();

        //获取文件后缀 这个后缀不带 .
        String extension="."+FilenameUtils.getExtension(upfile.getOriginalFilename());


        //生成新的文件名称
        String newFileName=new SimpleDateFormat("yyyyMMddHHmmss")+
                UUID.randomUUID().toString().replace("-","")+extension;

        //文件大小
        long size=upfile.getSize();

        //文件类型
        String type=upfile.getContentType();

        //处理文件上传
        String realPath =ResourceUtils.getURL("classpath:").getPath()+"static/files";

        //处理根据日期生成目录
        String dateFormat=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dateDirPath=realPath+"/"+dateFormat;


        File dateDir=new File(dateDirPath);

        //mkdirs 创建多级目录
        if(!dateDir.exists())dateDir.mkdirs();

        //处理文件上传
        upfile.transferTo(new File(dateDir,newFileName));



        //将文件信息放入数据库中
        MyFIle myFIle=new MyFIle();
        myFIle.setOldFileName(oldFileName).setNewFileName(newFileName)
             .setExt(extension).setSize(String.valueOf(size)).setType(type)
                .setPath("/files/"+dateFormat).setUid(uid);


        System.out.println("myfile"+ myFIle.toString());
        Integer flag=userFileService.save(myFIle);
        System.out.println("flag= "+flag);
        return  "redirect:/";
    }
}
