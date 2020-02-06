package com.han.service;

import com.han.dao.BookDao;
import com.han.dao.UserDao;
import com.han.dao.UserRentDao;
import com.han.entity.Book;
import com.han.entity.Books;
import com.han.entity.User;
import com.han.entity.UserRents;

import java.sql.SQLException;
import java.util.Date;

/**
 * @ClassName BookService
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/1 0001 20:23
 */
public class BookService {

    private BookDao bookDao = new BookDao();
    private UserDao userDao = new UserDao();
    private UserRentDao userRentDao = new UserRentDao();

    public Book getBookNumber(String bookName){
        Book book = bookDao.getOneByBookName(bookName);
        return book;
    }

    public int rent(String bookName, String username) {
        int result = 0;
        Book book = bookDao.getOneByBookName(bookName);
        User user = userDao.getOneByUsername(username);
        if (book.getAmount() > 0){
            book.setAmount(book.getAmount() - 1);
            bookDao.update(book);
            result = userRentDao.insert(book, user);
        } else {
            System.out.println("库存不够！！！");
        }
        return result;
    }

    public int updateBook(String bookName, String username) {
        int result = 0;
        User user = null;
        Books books = null;
        UserRents userRents = null;
        user = userDao.getOneByUsername(username);
        try {
            userRents = userRentDao.selectOne(user.getId(), bookName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userRents.setBook_amount(userRents.getBook_amount() - 1);
        userRents.setBook_state(1);
        userRents.setEnd_time(new Date());
        try {
            result = userRentDao.update(userRents);
        } catch (SQLException e) {
            result = 0;
        }
        Book book = bookDao.getOneByBookName(bookName);
        book.setAmount(book.getAmount() + 1);
        result = bookDao.update(book);
        return result;
    }
}
