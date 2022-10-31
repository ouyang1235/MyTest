package com.ouyang.demo.entity.controller;


import com.ouyang.demo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexController {

    @GetMapping("index")
    public String index(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return user!=null ? "welcome!"+user.toString() : "please login first";
    }

    @PostMapping("login")
    public String login(HttpServletRequest request,String name,String password){
        User admin = User.defaultUser();
        if (admin.getUserName().equals(name) && admin.getPassword().equals(password)){
            request.getSession().setAttribute("user",admin);
            return "login success!"+admin.toString();
        }else {
            return "login fail!";
        }
    }


    @GetMapping("/logout")
    public String login(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "has already logout";
    }



}
