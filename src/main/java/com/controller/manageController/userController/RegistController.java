package com.controller.manageController.userController;

import com.out.UserOut;
import com.pojo.User;
import com.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Created by Tellyes_worker on 2017/12/29/0029.
 */
@Controller
public class RegistController {
    private UserServiceImpl userService;
    @Autowired
    public void setUserService(UserServiceImpl userServiceInf){
        this.userService = userServiceInf;
    }

    private UserOut userOut;
    public RegistController(){
        userOut = new UserOut();
    }
    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public @ResponseBody UserOut register(@RequestParam(value = "name",required = false) String name, @RequestParam(value = "password",required = false) String password,@RequestParam(value = "creatTime",required = false) long creatTime){
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String n = name;
        String p = password;
        String t = sdf.format(creatTime);
        System.out.print(t);
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(n);
        user.setPassword(p);
        user.setCreatTime(t);
        if(userService.addUser(user) == true){
            userOut.setId("0");
        }else {
            userOut.setId("1");
        }
        return userOut;
    }
}
