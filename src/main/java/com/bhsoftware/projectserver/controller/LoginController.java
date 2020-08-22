package com.bhsoftware.projectserver.controller;

import com.bhsoftware.projectserver.entity.User;
import com.bhsoftware.projectserver.result.Result;
import com.bhsoftware.projectserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value="/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session){
        String username = requestUser.getUsername();

        User user= userService.get(username, requestUser.getPassword());
        if(user == null){
            return new Result(400);
        }else{
            session.setAttribute("user", user);
            return new Result(200);
        }
    }
}
