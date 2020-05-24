package com.file.filetest.Contoller;

import com.file.filetest.pojo.MyUser;
import com.file.filetest.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class MyUserController {
    @Autowired
    MyUserService myUserService;


    @PostMapping("tologin")
    public String tologin(@ModelAttribute MyUser myUser, HttpSession session){
       MyUser myUser1=myUserService.login(myUser);
        if(myUser1!=null){
            session.setAttribute("myuser",myUser1);
            System.out.println(myUser1.toString());
            return "redirect:/file/findAll";
        }else {
            return  "redirect:/";
        }

    }
}
