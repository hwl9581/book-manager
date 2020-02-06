package com.han.dao;

import com.han.entity.Book;
import com.han.entity.Books;
import com.han.entity.User;
import com.han.util.DBUtil;
import com.han.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BookDao
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/1 0001 19:38
 */
public class BookDao {

    public List<Book> selectAllBook(){
        ArrayList<Book> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("select * from books");
            rs = pstmt.executeQuery();
            while (rs.next()){
               Book book = Book.builder()
                                .id(rs.getLong("id"))
                                .number(rs.getString("number"))
                                .bookName(rs.getString("book_name"))
                                .author(rs.getString("author"))
                                .amount(rs.getInt("amount"))
                                .createTime(sdf.parse(rs.getString("create_time")))
                                .updateTime(sdf.parse(rs.getString("update_time")))
                                .build();
               list.add(book);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        DBUtil.closeAll(con, pstmt, rs);
        return list;
    }

    public int update(Book book){
        Connection con = null;
        PreparedStatement pstmt = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int result = 0;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("update books set number = ?, book_name = ?, author = ?, amount = ?, create_time = ?, update_time = ? where id = ?");
            pstmt.setString(1, book.getNumber());
            pstmt.setString(2, book.getBookName());
            pstmt.setString(3, book.getAuthor());
            pstmt.setInt(4, book.getAmount());
            pstmt.setString(5, sdf.format(book.getCreateTime()));
            pstmt.setString(6, sdf.format(book.getUpdateTime()));
            pstmt.setLong(7, book.getId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Book getOneByBookName(String bookName){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Book book = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("select * from books where book_name = ?");
            pstmt.setString(1, bookName);
            rs = pstmt.executeQuery();
            if (rs.next()){
                book = new Book();
                book.setId(rs.getLong("id"));
                book.setNumber(rs.getString("number"));
                book.setBookName(rs.getString("book_name"));
                book.setAuthor(rs.getString("author"));
                book.setAmount(rs.getInt("amount"));
                book.setCreateTime(sdf.parse(rs.getString("create_time")));
                book.setUpdateTime(sdf.parse(rs.getString("update_time")));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return book;
    }


}
