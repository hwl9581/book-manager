package com.han.dao;

import com.han.entity.*;
import com.han.util.DBUtil;
import com.han.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BookRentDao
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/1 0001 21:09
 */
public class UserRentDao {

    private BookDao bookDao = new BookDao();
    private UserDao userDao = new UserDao();

    public int insert(Book book, User user){
        int result = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("insert into user_rent(user_id, book_number, book_name, book_amount, create_time, book_state) values(?,?,?,?,?,?)");
            pstmt.setLong(1, user.getId());
            pstmt.setString(2, book.getNumber());
            pstmt.setString(3, book.getBookName());
            pstmt.setInt(4, 1);
            pstmt.setString(5, sdf.format(new Date()));
            pstmt.setInt(6, 0);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.closeAll(con, pstmt, null);
        return result;
    }

    public List<UserRent> selectAll(Long userId){
        ArrayList<UserRent> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("select * from user_rent where user_id = ?");
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()){
                UserRent userRent = UserRent.builder()
                                            .id(rs.getLong("id"))
                                            .userId(rs.getLong("user_id"))
                                            .bookName(rs.getString("book_name"))
                                            .bookNumber(rs.getString("book_number"))
                                            .bookAmount(rs.getInt("book_amount"))
                                            .bookState(rs.getInt("book_state"))
                                            .createTime(sdf.parse(rs.getString("create_time")))
                                            .build();
                if (rs.getString("end_time") == null){
                    userRent.setEndTime(null);
                } else {
                    userRent.setEndTime(sdf.parse(rs.getString("end_time")));
                }
                list.add(userRent);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int update(UserRents userRents) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "update user_rent set user_id = ?, book_number = ?, book_name = ?, book_amount = ?, create_time = ?, end_time = ?, book_state = ? where id = ?";
        Object[] params = {userRents.getUser_id(), userRents.getBook_number(), userRents.getBook_name(), userRents.getBook_amount(), userRents.getCreate_time(), userRents.getEnd_time(), userRents.getBook_state(), userRents.getId()};
        int result = qr.update(sql, params);
        return result;
    }

    public UserRents selectOne(Long userId, String bookName) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        ArrayList<Object> params = new ArrayList<>();
        String sql = "select * from user_rent where user_id = ? and book_name = ?";
        UserRents user = qr.query(sql, new BeanHandler<>(UserRents.class), userId, bookName);
        return user;
    }

    public List<BookCount> countBook() throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "select book_name, count(book_name) as count from user_rent group by book_name order by count desc";
        List<BookCount> list = qr.query(sql, new BeanListHandler<>(BookCount.class));
        return list;
    }
}
