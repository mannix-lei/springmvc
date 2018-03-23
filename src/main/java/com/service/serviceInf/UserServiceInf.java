package com.service.serviceInf;

import com.out.UserOut;
import com.pojo.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Tellyes_worker on 2017/12/4/0004.
 */
public interface UserServiceInf {
     User getUserById(String id);
     List<User> findAllUser();
     boolean addUser(User user);
     String login(String name);
     boolean register(User user);
     void delUser(String id);
     User findUserInfo(String name);
     UserOut updateUser(User user);
}
