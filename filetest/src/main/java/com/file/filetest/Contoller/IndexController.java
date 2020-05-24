package com.file.filetest.Contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {

    @GetMapping("/")
    public String goIndex(){
        System.out.println("进入index页面");
        return  "login";
    }

}
