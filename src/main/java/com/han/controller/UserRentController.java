package com.han.controller;

import com.han.entity.ElecBooks;
import com.han.entity.User;
import com.han.entity.UserRent;
import com.han.service.BuyElecBooksService;
import com.han.service.ElecBooksService;
import com.han.service.UserRentService;
import com.han.service.UserService;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName UserRentController
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/1 0001 21:43
 */
public class UserRentController {

    private UserService userService = new UserService();
    private UserRentService userRentService = new UserRentService();
    private ElecBooksService elecBooksService = new ElecBooksService();
    private BuyElecBooksService buyElecBooksService = new BuyElecBooksService();
    private Scanner input = new Scanner(System.in);

    public void rentHistory(String username){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<UserRent> list = userRentService.rentHistory(username);
        System.out.println("书籍名称" + "\t\t" + "书籍编号" + "\t\t" + "借阅数量" + "\t\t" + "借阅时间"  + "\t\t\t" + "            归还时间" + "\t\t\t\t\t" + "借阅状态");
        for (UserRent ur : list){
            String state = "";
            String endTime = "";
            if (ur.getBookState() == 0){
                state = "未归还";
            } else {
                state = "已归还";
            }
            if (ur.getEndTime() == null) {
                endTime = "无                 ";
            } else {
                endTime = sdf.format(ur.getEndTime());
            }
            System.out.println(ur.getBookName() + "\t\t" + ur.getBookNumber() + "\t\t" + ur.getBookAmount() + "\t\t    " + sdf.format(ur.getCreateTime()) + "\t\t\t" + endTime + "\t\t" + state);
        }
    }

    public void buyElecBook(String username){
        List<ElecBooks> elecBooks = elecBooksService.selectElecBook();
        System.out.println("**********正在热卖的电子书**********");
        System.out.println("电子书名称" + "\t\t" + "电子书作者");
        for (ElecBooks eb : elecBooks){
            System.out.println(eb.getElec_book_name() + "\t\t" + eb.getElec_book_author());
        }
        System.out.println("请输入您要购买的电子书名：");
        String bookName = input.next();
        int result = buyElecBooksService.insertBook(bookName, username);
        if (result > 0){
            System.out.println("购买成功！");
        } else {
            System.out.println("购买失败！");
        }
    }

    public void watchBook(String username){
        System.out.println("您已购买的电子书：");
        System.out.println("电子书名称" + "\t\t" + "电子书作者");
        List<ElecBooks> list = elecBooksService.selectElecBook(username);
        for (ElecBooks eb1 : list){
            System.out.println(eb1.getElec_book_name() + "\t\t" + eb1.getElec_book_author());
        }
        System.out.println("请输入您要阅读的电子书名称：");
        String bookName = input.next();
        ElecBooks elecBooks = elecBooksService.selectOneElecBook(bookName);
        boolean flag = false;
        for (ElecBooks eb2 : list){
            if (elecBooks.getId() == eb2.getId()){
                flag = true;
            }
        }
        if (flag){
            File file = new File(elecBooks.getElec_book_address());
            String[] chapter = file.list();
            for (int i = 0; i < chapter.length; i++){
                boolean flag1 = true;
                FileReader fr = null;
                BufferedReader br = null;
                try {
                    fr = new FileReader(elecBooks.getElec_book_address() + "//" + chapter[i]);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                br = new BufferedReader(fr);
                String data = null;
                try {
                    while ((data = br.readLine()) != null){
                        System.out.println(data);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println("请输入：1.下一章 --【任意数字键退出】");
                int choose = input.nextInt();
                switch (choose){
                    case 1:
                        break;
                    default: flag1 = false;
                        break;
                }
                if (!flag1){
                    break;
                }
            }

        } else {
            System.out.println("您未购买该书籍！！！");
        }
    }
}
