package com.service.impl;

import com.dao.UserDaoInf;
import com.out.UserOut;
import com.service.serviceInf.UserServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Tellyes_worker on 2017/12/4/0004.
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserServiceInf {

    private UserDaoInf userDao;
    @Autowired
    public void setUserDao(UserDaoInf userDao){
        this.userDao = userDao;
    }
    @Override
    public User getUserById(String id) {
        return this.userDao.findUserById(id);
    }

    /** 获取用户列表 */
    @Override
    public List<User> findAllUser() {
        return this.userDao.findAllUser();
    }
    /** 添加用户 */
    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }
    /** 登录 */
    @Override
    public String login(String name){
        return userDao.findUserByName(name);
    }
    /** 注册 */
    @Override
    public boolean register(User user){
        userDao.addUser(user);
        return true;
    }
    /** 删除用户 */
    @Override
    public void delUser(String id) {
        userDao.delUserById(id);
    }
    /** 根据用户名查询用户 */
    @Override
    public User findUserInfo(String name) {
        User user = userDao.findUserInfo(name);
        return  user;
    }

    @Override
    public UserOut updateUser(User user) {
        return userDao.updateUser(user);
    }
}
