package com.han.demo;

import com.han.controller.ManagerController;
import com.han.controller.UserController;
import com.han.dao.BookDao;
import com.han.entity.Book;
import com.han.util.DBUtil;
import com.han.util.EmailUtil;
import com.han.util.MD5Util;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.mail.EmailException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName Demo01
 * @Description TODO
 * @Author HanWL
 * @Since 2020/1/28 0028 16:13
 */
public class Demo01 {

    public static void main(String[] args) throws Exception{

        ManagerController mc = new ManagerController();
        mc.addElecBook();

    }

    private static void test06() {
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.selectAllBook();
        books.stream().forEach(System.out::println);
    }

    private static void test05() {
        try {
            while(true){
                EmailUtil.sendCode("1365616025@qq.com","打死你，你就和蛆一样");
            }
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    private static void test04() {
        UserController userController = new UserController();
        userController.login();
    }

    private static void test03() {
        UserController userController = new UserController();
        userController.register();
    }

    private static void test02() throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = con.prepareStatement("insert into users(username, password) values(?,?)");
        pstmt.setString(1, "han");
        pstmt.setString(2, MD5Util.encrpyPassword("123456"));
        int result = pstmt.executeUpdate();
        System.out.println(result);
    }

    private static void test01() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String str = "何甜甜";
        String s = MD5Util.encrpyPassword(str);
        System.out.println(s);
    }


}
