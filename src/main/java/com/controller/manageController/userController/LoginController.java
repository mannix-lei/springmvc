package com.controller.manageController.userController;

import com.out.UserOut;
import com.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Tellyes_worker on 2017/12/29/0029.
 */
@Controller
public class LoginController{

    private UserOut userOut;
    private UserServiceImpl userService;
    public LoginController(){
        userOut = new UserOut();
    }
    @Autowired
    public void setUserService(UserServiceImpl userServiceInf){
        this.userService = userServiceInf;
    }

    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody UserOut login(@RequestParam(value = "name",required = false) String name, @RequestParam(value = "password",required = false) String password){
        String msg = "";
        String pwd = password;

        if(userService.login(name)!= null && pwd.equals(userService.login(name))){
            msg = "welcome" + name;
            userOut.setId("0");
        }
        if(userService.login(name)==null){
            msg = "user is not exist";
            userOut.setId("1");
        }
        if(userService.login(name) != null && !pwd.equals(userService.login(name))){
            msg = "password is wrong";
            userOut.setId("2");
        }
        userOut.setMsg(msg);
        return userOut;
    }
}
