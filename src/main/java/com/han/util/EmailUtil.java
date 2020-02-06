package com.han.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * @ClassName EmailUtil
 * @Description TODO
 * @Author HanWL
 * @Since 2020/1/31 0031 14:38
 */
public class EmailUtil {

    public static String sendCode(String emailAddress, String code) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.qq.com");
        email.setCharset("utf-8");
        email.addTo(emailAddress);
        email.setFrom("1298949507@qq.com","图书系统管理员");
        email.setAuthentication("1298949507@qq.com","budelehgboptjjaj");
        email.setSubject("验证码");
        //email.setMsg(code);
        email.setMsg("验证码:【" + code + "】--请确保是您本人操作,如果不是您本人操作请联系图书系统管理员");
        String send = email.send();
        return send;
    }

}
