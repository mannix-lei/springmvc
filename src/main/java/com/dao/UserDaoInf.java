package com.dao;

import com.controller.SqlSession.MyBatisSqlSession;
import com.mapper.IUserMapper;
import com.out.UserOut;
import com.pojo.User;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * Created by Tellyes_worker on 2017/12/4/0004.
 */
@Repository
public class UserDaoInf {

    /**
     * 新增用户
     * @param user
     */
    public boolean addUser(User user){
        if(findUserByName(user.getName()) == null){
           User user1 = new User();
           user1.setId(user.getId());
           user1.setName(user.getName());
           user1.setPassword(user.getPassword());
           user1.setCreatTime(user.getCreatTime());
           MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
           ms.add(user1);
           ms.commit();
           ms.close();
            return true;
        }else {
            return false;
        }
    }


    /**
     * 删除用户
     * @param id
     */
    public void delUserById(String id){
        MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
        ms.del(id);
        ms.commit();
        ms.close();
        System.out.println("删除成功");
    }

    /**
     * 更新用户
     * @param user
     */
    public UserOut updateUser(User user){
        UserOut userOut = new UserOut();
        MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
        ms.update(user);
        ms.commit();
        ms.close();
        userOut.setId("0");
        return userOut;
    }

    /**
     * 获取用户列表
     * @return
     */
    public List<User> findAllUser(){
        MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
        List<User> list = ms.findAll();
        ms.close();
        return list;
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public User findUserById(String id){
        MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
        Map<String,String>map = new HashMap<String, String>();
        map.put("id",id);
        User user = ms.findFirst(map);
        return user;
    }

    /**
     * 用户名账号登录
     * @param name
     * @return
     */
    public String findUserByName(String name){
        String pwd = "";
        MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
        Map<String,String> map = new HashMap<String, String>();
        map.put("name",name);
        if(ms.findFirst(map)==null){
            pwd = null;
        }else {
            User user1 = ms.findFirst(map);
            pwd = user1.getPassword();
        }
        return pwd;
    }

    /**
     * 输入用户名查询用户详细信息
     * @param name
     * @return
     */
    public User findUserInfo(String name){
        MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
        Map<String,String> map = new HashMap<String, String>();
        map.put("name",name);
        User user = ms.findFirst(map);
        return user;
    }
}
