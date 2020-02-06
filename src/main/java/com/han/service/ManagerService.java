package com.han.service;

import com.han.dao.BooksDao;
import com.han.entity.Books;

import java.sql.SQLException;
import java.util.Date;

/**
 * @ClassName ManagerService
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/4 0004 17:15
 */
public class ManagerService {

    private BooksDao booksDao = new BooksDao();

    public int insertBooks(Books books) {
        int result = 0;
        try {
            result = booksDao.insert(books);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteBooks(Books books) {
        int result = 0;
        try {
            result = booksDao.delete(books);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateBooks(Books books) {
        int result = 0;
        Books books1 = null;
        try {
            books1 = booksDao.selectOne(Books.builder().number(books.getNumber()).build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (books.getBook_name() != null && !books.getBook_name().trim().equals("")){
            books1.setBook_name(books.getBook_name());
        }
        if (books.getAuthor() != null && books.getAuthor().trim() != ""){
            books1.setAuthor(books.getAuthor());
        }
        if (books.getAmount() != null ){
            books1.setAmount(books.getAmount());
        }
        books1.setUpdate_time(new Date());

        try {
            result = booksDao.update(books1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
