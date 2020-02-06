package com.han.dao;

import com.han.entity.User;
import com.han.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author HanWL
 * @Since 2020/1/31 0031 15:25
 */
public class UserDao {

    public int insert(User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int result = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("insert into users(username, password, real_name, email_address, email_state, create_time, update_time) values(?,?,?,?,?,?,?)");
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRealName());
            pstmt.setString(4, user.getEmailAddress());
            pstmt.setInt(5, user.getEmailState());
            pstmt.setString(6, sdf.format(user.getCreateTime()));
            pstmt.setString(7, sdf.format(user.getUpdateTime()));
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.closeAll(con, pstmt, null);
        return result;
    }

    public int getAllUsername(String username){
        int result = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("select count(*) from users where username = ? ");
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.closeAll(con, pstmt, null);
        return result;
    }

    public int update(User user){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int result = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("update users set id = ?, username = ?, password = ?, real_name = ?, email_address = ?, email_state = ?, create_time = ?, update_time = ? where username = ?");
            pstmt.setLong(1, user.getId());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRealName());
            pstmt.setString(5, user.getEmailAddress());
            pstmt.setInt(6, user.getEmailState());
            pstmt.setString(7, sdf.format(user.getCreateTime()));
            pstmt.setString(8, sdf.format(user.getUpdateTime()));
            pstmt.setString(9, user.getUsername());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.closeAll(con, pstmt, null);
        return result;
    }

    public User getOneByUsername(String username){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = new User();
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("select * from users where 1 = 1 and username = ?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            if (rs.next()){
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRealName(rs.getString("real_name"));
                user.setEmailAddress(rs.getString("email_address"));
                user.setEmailState(rs.getInt("email_state"));
                user.setCreateTime(sdf.parse(rs.getString("create_time")));
                user.setUpdateTime(sdf.parse(rs.getString("update_time")));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        DBUtil.closeAll(con, pstmt, rs);
        return user;
    }
}
