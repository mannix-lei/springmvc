package com.controller.manageController.userController;

import com.out.UserOut;
import com.pojo.User;
import com.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tellyes_worker on 2017/12/4/0004.
 */
@Controller
public class UserManageController {

    private UserServiceImpl userService;
    @Autowired
    public void setUserService(UserServiceImpl userServiceInf){
        this.userService = userServiceInf;
    }
    /**
     * 使用json响应内容
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public @ResponseBody List<User> showUser(){
        List<User> list = userService.findAllUser();
        return list;
    }

    /**
     * 删除用户
     * @param id
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = "/delUser",method = RequestMethod.POST)
    public @ResponseBody boolean delUser(@RequestParam(value = "id",required = false) String id){
        String userid = id;
        userService.delUser(userid);
        return true;
    }

    /**
     * 根据用户名查询用户详细信息
     * @param name
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = "/findUserByID",method = RequestMethod.POST)
    public @ResponseBody User findUserInfo(@RequestParam(value = "name",required = false) String name){
        String n = name;
        User user = userService.findUserInfo(n);
        return user;
    }

    /**
     * 修改用户信息
     * @param
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public @ResponseBody UserOut updateUser(@RequestParam(value = "id",required = false)String id,@RequestParam(value = "name",required = false)String name,@RequestParam(value = "password",required = false) String password,@RequestParam(value = "creatTime",required = false)String creatTime){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setCreatTime(creatTime);
        userService.updateUser(user);
        UserOut userOut = new UserOut();
        userOut.setId("0");
        return userOut;
    }
}
