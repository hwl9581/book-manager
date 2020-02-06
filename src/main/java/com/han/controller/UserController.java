package com.han.controller;

import com.han.entity.User;
import com.han.service.UserService;
import com.han.util.EmailUtil;
import com.han.util.MD5Util;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.mail.EmailException;

import javax.mail.Session;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @ClassName RegisterController
 * @Description TODO
 * @Author HanWL
 * @Since 2020/1/31 0031 14:50
 */
public class UserController {

    private Scanner input = new Scanner(System.in);
    private UserService userService = new UserService();

    public void register(){
        String username = "";
        String password = "";
        String confim_pwd = "";
        String emailAddress = "";

        boolean usernameFlag = true;
        do {
            System.out.println("请输入您的用户名：");
            username = input.next();
            usernameFlag = userService.judgeUsername(username);
            if (usernameFlag){
                System.out.println("用户名已存在！！！");
            }
        }while (usernameFlag);

        boolean passwordFlag = true;
        do {
            System.out.println("请输入您的密码：");
            password = input.next();
            System.out.println("请再次输入您的密码：");
            confim_pwd = input.next();
            passwordFlag = password.equals(confim_pwd);
            if (!passwordFlag){
                System.out.println("两次密码输入不一致！！！");
            }
        }while (!passwordFlag);
        System.out.println("请输入您的真实姓名");
        String realName = input.next();
        boolean matches = false;
        do {
            System.out.println("请输入您的邮箱：");
            emailAddress = input.next();
            String regex = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";
            matches = Pattern.matches(regex, emailAddress);
            if (!matches){
                System.out.println("请输入正确的邮箱地址！！！");
            }
        }while (!matches);
        User user = new User(null, username, password, realName, emailAddress, 0, new Date(), new Date());
        int result = userService.register(user);
        if (result > 0){
            System.out.println("注册成功！！！");
        }
    }

    public void activateEmail(String username){
        int random = RandomUtils.nextInt(100000, 999999);
        User user = userService.getOne(username);
        try {
            EmailUtil.sendCode(user.getEmailAddress(), random + "");
            System.out.println("我们已向您的邮箱发送验证码，请输入验证码以确认激活邮箱！");
        } catch (EmailException e) {
            e.printStackTrace();
        }
        int userInput = input.nextInt();
        if (random == userInput){
            User user1 = new User();
            user1.setId(user.getId());
            user1.setUsername(user.getUsername());
            user1.setPassword(user.getPassword());
            user1.setRealName(user.getRealName());
            user1.setEmailAddress(user.getEmailAddress());
            user1.setEmailState(1);
            user1.setCreateTime(user.getCreateTime());
            user1.setUpdateTime(new Date());
            int result = userService.activateEmail(user1);
            if (result > 0){
                System.out.println("激活成功!!!");
            }
        }
    }

    public void forgetPassword(){
        System.out.println("请输入您的账户：");
        String username = input.next();
        User user = userService.getOne(username);
        int random = RandomUtils.nextInt(100000, 999999);
        try {
            EmailUtil.sendCode(user.getEmailAddress(), random + "");
        } catch (EmailException e) {
            e.printStackTrace();
        }
        System.out.println("我们已经往您的邮箱里面发送验证码，请您接收：");
        int userInput = input.nextInt();

        if (random == userInput){
            boolean flag = true;
            String password = "";
            do {
                System.out.println("请输入您的新密码：");
                password = input.next();
                System.out.println("请确认您的密码：");
                String newPassword = input.next();
                flag = password.equals(newPassword);
                if (!flag){
                    System.out.println("两次输入的不一致");
                }
            }while(!flag);
            try {
                user.setPassword(MD5Util.encrpyPassword(password));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            int result = userService.updatePassword(user);
            if (result > 0){
                System.out.println("密码修改成功!");
            }
        } else {
            System.out.println("验证码错误！！！");
        }
    }

    public String login(){
        System.out.println("请输入您的账户：");
        String username = input.next();
        System.out.println("请输入您的密码：");
        String password = input.next();
        boolean result = userService.login(username, password);
        if (result){
            System.out.println("登陆成功");
            return username;
        } else {
            System.out.println("账号或密码错误！！！");
        }
        return null;
    }

}
