package com.han;

import com.han.controller.BookController;
import com.han.controller.ManagerController;
import com.han.controller.UserController;
import com.han.controller.UserRentController;

import java.util.Scanner;

/**
 * @ClassName book_manager
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/1 0001 11:21
 */
public class book_manager {

    private static UserController uc = new UserController();
    private static BookController bc = new BookController();
    private static UserRentController urc = new UserRentController();
    private static ManagerController mc = new ManagerController();
    private static Scanner input = new Scanner(System.in);
    private static String username = "";

    public static void main(String[] args) {
        show();
        boolean flag = true;
        do {
            option();
            int choose = input.nextInt();
            switch (choose) {
                case 1:
                    username = uc.login();
                    if (username != null) {
                        if (username.equals("admin")) {
                            managerOption();
                        } else {
                            userOption();
                        }
                    }
                    break;
                case 2:
                    uc.register();
                    break;
                case 3:
                    uc.forgetPassword();
                    break;
                default:
                    flag = false;
                    break;
            }
        } while (flag);

    }

    private static void userOption() {
        boolean flag = true;
        do {
            System.out.println("请选择您要执行的操作：1.借阅图书 2.退还图书 3.激活邮箱 4.查询图书编号及库存 5.我的借阅历史 6.购买电子书 7.在线阅读我的电子书--【按任意数字键退出】");
            int result = input.nextInt();
            switch (result) {
                case 1:
                    bc.rentBook(username);
                    break;
                case 2:
                    bc.backBook(username);
                    break;
                case 3:
                    uc.activateEmail(username);
                    break;
                case 4:
                    bc.getBookNumber();
                    break;
                case 5:
                    urc.rentHistory(username);
                    break;
                case 6:
                    urc.buyElecBook(username);
                    break;
                case 7:
                    urc.watchBook(username);
                    break;
                default:
                    flag = false;
                    break;
            }
        } while (flag);
    }

    public static void managerOption() {
        boolean flag = true;
        do {
            System.out.println("请选择：1.增加图书 2.删除图书 3.修改图书 4.上传电子书 5.统计热卖图书--【按任意数字键退出】");
            int choose = input.nextInt();
            switch (choose) {
                case 1:
                    mc.addBooks();
                    break;
                case 2:
                    mc.deleteBooks();
                    break;
                case 3:
                    mc.updateBooks();
                    break;
                case 4:
                    mc.addElecBook();
                    break;
                case 5:
                    mc.hotBook();
                    break;
                default:
                    flag = false;
                    break;
            }
        } while (flag);
    }

    private static void option() {
        System.out.println("请先选择：1.登陆 2.注册 3.忘记密码 --【按任意其他数字键退出】");
    }

    private static void show() {
        System.out.println("**********************");
        System.out.println("**欢迎进入图书管理系统**");
        System.out.println("**********************");
    }

}
