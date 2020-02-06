package com.han.demo;

import com.han.entity.Book;
import com.han.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * @ClassName DBUtilDemo
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/3 0003 10:25
 */
public class DBUtilDemo {

    public static void main(String[] args) throws SQLException {

        DBUtilDemo dd = new DBUtilDemo();
        dd.selectAll();

    }

    public void selectAll() throws SQLException {
        QueryRunner qr = new QueryRunner();
        //1.使用query方法实现查询
        String sql = "select * from books";
        /*Object[] query = qr.query(DBUtil.getConnection(), sql, new ArrayHandler());
        System.out.println(Arrays.toString(query));*/

        Book book = qr.query(DBUtil.getConnection(),  sql, new BeanHandler<>(Book.class));
        System.out.println(book);
    }

    public void selectCount() throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql = "select count(*) from books";
        qr.query(DBUtil.getConnection(), sql, new ScalarHandler<>());
    }

}
