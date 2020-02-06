package com.han.dao;

import com.han.entity.Books;
import com.han.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BooksDao
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/4 0004 20:30
 */
public class BooksDao {

    public int insert(Books books) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "insert into books(number, book_name, author, amount, create_time, update_time) values(?,?,?,?,?,?)";
        Object[] params = {books.getNumber(), books.getBook_name(), books.getAuthor(), books.getAmount(), books.getCreate_time(), books.getUpdate_time()};
        int result = qr.update(sql, params);
        return result;
    }

    public int delete(Books books) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "delete from books where 1 = 1";
        ArrayList<Object> params = new ArrayList<>();
        if (books.getId() != null){
            sql += " and id = ?";
            params.add(books.getId());
        }
        if (books.getNumber() != null){
            sql += " and number = ?";
            params.add(books.getNumber());
        }
        if (books.getBook_name() != null){
            sql += " and book_name = ?";
            params.add(books.getBook_name());
        }
        if (books.getAuthor() != null){
            sql += " and author = ?";
            params.add(books.getAuthor());
        }
        if (books.getAmount() != null){
            sql += " and amount = ?";
            params.add(books.getAmount());
        }
        if (books.getCreate_time() != null){
            sql += " and create_time = ?";
            params.add(books.getCreate_time());
        }
        if (books.getUpdate_time() != null){
            sql += " and update_time = ?";
            params.add(books.getUpdate_time());
        }
        int result = qr.update(sql, params.toArray());
        return result;
    }

    public int update(Books books) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "update books set number = ?, book_name = ?, author = ?, amount = ?, create_time = ?, update_time = ? where id = ?";
        Object[] params = {books.getNumber(), books.getBook_name(), books.getAuthor(), books.getAmount(), books.getCreate_time(), books.getUpdate_time(), books.getId()};
        int result = qr.update(sql, params);
        return result;
    }

    public List<Books> selectAll(Books books) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "select * from books where 1 = 1";
        ArrayList<Object> params = new ArrayList<>();
        if (books.getId() != null){
            sql += " and id = ?";
            params.add(books.getId());
        }
        if (books.getNumber() != null){
            sql += " and number = ?";
            params.add(books.getNumber());
        }
        if (books.getBook_name() != null){
            sql += " and book_name = ?";
            params.add(books.getBook_name());
        }
        if (books.getAuthor() != null){
            sql += " and author = ?";
            params.add(books.getAuthor());
        }
        if (books.getAmount() != null){
            sql += " and amount = ?";
            params.add(books.getAmount());
        }
        if (books.getCreate_time() != null){
            sql += " and create_time = ?";
            params.add(books.getCreate_time());
        }
        if (books.getUpdate_time() != null){
            sql += " and update_time = ?";
            params.add(books.getUpdate_time());
        }
        List<Books> list = qr.query(sql, new BeanListHandler<>(Books.class), params.toArray());
        return list;
    }

    public Books selectOne(Books books) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "select * from books where 1 = 1";
        ArrayList<Object> params = new ArrayList<>();
        if (books.getId() != null){
            sql += " and id = ?";
            params.add(books.getId());
        }
        if (books.getNumber() != null){
            sql += " and number = ?";
            params.add(books.getNumber());
        }
        if (books.getBook_name() != null){
            sql += " and book_name = ?";
            params.add(books.getBook_name());
        }
        if (books.getAuthor() != null){
            sql += " and author = ?";
            params.add(books.getAuthor());
        }
        if (books.getAmount() != null){
            sql += " and amount = ?";
            params.add(books.getAmount());
        }
        if (books.getCreate_time() != null){
            sql += " and create_time = ?";
            params.add(books.getCreate_time());
        }
        if (books.getUpdate_time() != null){
            sql += " and update_time = ?";
            params.add(books.getUpdate_time());
        }
        Books book = qr.query(sql, new BeanHandler<>(Books.class), params.toArray());
        return book;
    }
}
