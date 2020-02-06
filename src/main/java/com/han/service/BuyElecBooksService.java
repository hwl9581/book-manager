package com.han.service;

import com.han.dao.BuyElecBooksDao;
import com.han.dao.ElecBooksDao;
import com.han.dao.UserDao;
import com.han.entity.BuyElecBooks;
import com.han.entity.ElecBooks;
import com.han.entity.User;

import java.sql.SQLException;

/**
 * @ClassName BuyElecBooksService
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/5 0005 22:20
 */
public class BuyElecBooksService {

    private ElecBooksDao elecBooksDao = new ElecBooksDao();
    private BuyElecBooksDao buyElecBooksDao = new BuyElecBooksDao();
    private UserDao userDao = new UserDao();

    public int insertBook(String bookName, String username){
        ElecBooks elecBooks = null;
        int result = 0;
        try {
            elecBooks = elecBooksDao.selectOne(ElecBooks.builder().elec_book_name(bookName).build());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User user = userDao.getOneByUsername(username);
        try {
            result = buyElecBooksDao.insert(BuyElecBooks.builder().user_id(user.getId()).elec_book_id(elecBooks.getId()).build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
