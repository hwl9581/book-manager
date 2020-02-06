package com.han.service;

import com.han.dao.UserRentDao;
import com.han.entity.BookCount;
import com.han.entity.User;
import com.han.entity.UserRent;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName UserRentService
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/1 0001 21:45
 */
public class UserRentService {

    private UserService userService = new UserService();
    private UserRentDao userRentDao = new UserRentDao();

    public List<UserRent> rentHistory(String username){
        User user = userService.getOne(username);
        List<UserRent> list = userRentDao.selectAll(user.getId());
        return list;
    }

    public void backBook(String username, String bookName) {
        User user = userService.getOne(username);
    }

    public List<BookCount> getHotBook(){
        List<BookCount> bookCounts = null;
        try {
            bookCounts = userRentDao.countBook();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookCounts;
    }
}
