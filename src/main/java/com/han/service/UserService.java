package com.han.service;

import com.han.dao.UserDao;
import com.han.entity.User;
import com.han.util.MD5Util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName RegisterService
 * @Description TODO
 * @Author HanWL
 * @Since 2020/1/31 0031 15:21
 */
public class UserService {

    private UserDao userDao = new UserDao();

    public int register(User user) {
        UserDao userDao = new UserDao();
        try {
            user.setPassword(MD5Util.encrpyPassword(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int result = userDao.insert(user);
        return result;
    }

    public boolean judgeUsername(String username){
        UserDao userDao = new UserDao();
        int result = userDao.getAllUsername(username);
        if (result > 0){
            return true;
        } else {
            return false;
        }
    }

    public int activateEmail(User user){
        int result = userDao.update(user);
        return result;
    }

    public User getOne(String username){
        User user = userDao.getOneByUsername(username);
        return user;
    }

    public int updatePassword(User user){
        int result = userDao.update(user);
        return result;
    }

    public boolean login(String username, String password) {
        User user = userDao.getOneByUsername(username);
        boolean result = false;
        try {
            result = user.getPassword().equals(MD5Util.encrpyPassword(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
