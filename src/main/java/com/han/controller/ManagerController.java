package com.han.controller;

import com.han.entity.Book;
import com.han.entity.BookCount;
import com.han.entity.Books;
import com.han.entity.ElecBooks;
import com.han.service.ElecBooksService;
import com.han.service.ManagerService;
import com.han.service.UserRentService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName ManagerController
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/4 0004 17:15
 */
public class ManagerController {

    private ManagerService managerService = new ManagerService();
    private ElecBooksService elecBooksService = new ElecBooksService();
    private UserRentService userRentService = new UserRentService();
    private Scanner input = new Scanner(System.in);

    public void addBooks() {
        System.out.println("****************增加书籍操作****************");
        System.out.println("请输入书籍编号");
        String number = input.next();
        System.out.println("请输入书籍名称");
        String bookName = input.next();
        System.out.println("请输入作者");
        String author = input.next();
        System.out.println("请输入入库的数量");
        int amount = input.nextInt();
        int result = managerService.insertBooks(Books.builder().number(number).book_name(bookName)
                                    .author(author).amount(amount).create_time(new Date()).update_time(new Date()).build());
        if (result > 0){
            System.out.println("新增图书成功！");
        } else {
            System.out.println("新增图书失败！");
        }

    }

    public void deleteBooks() {
        System.out.println("****************删除书籍操作****************");
        System.out.println("请输入您要删除的图书编号");
        String number = input.next();
        int result = managerService.deleteBooks(Books.builder().number(number).build());
        if (result > 0){
            System.out.println("删除成功!");
        } else {
            System.out.println("删除失败!");
        }
    }

    public void updateBooks() {
        System.out.println("****************更新书籍操作****************");
        System.out.println("输入您要更新的书籍编号");
        String number = input.nextLine();
        System.out.println("更新书籍名称");
        String bookName = input.nextLine();
        System.out.println("更新作者");
        String author = input.nextLine();
        System.out.println("更新入库的数量");
        int amount = input.nextInt();
        int result = managerService.updateBooks(Books.builder().number(number).book_name(bookName)
                .author(author).amount(amount).update_time(new Date()).build());
        if (result > 0){
            System.out.println("更新成功！");
        } else {
            System.out.println("更新失败！");
        }
    }

    public void addElecBook(){
        System.out.println("****************上传电子书操作****************");
        System.out.println("输入电子书名");
        String elecBookName  = input.next();
        System.out.println("输入电子书作者");
        String elecBookAuthor = input.next();
        System.out.println("上传地址");
        String elecBookAddress = input.next();
        int result = elecBooksService.insertElecBook(ElecBooks.builder().elec_book_name(elecBookName).elec_book_author(elecBookAuthor).elec_book_address(elecBookAddress).create_time(new Date()).build());
        if (result > 0){
            System.out.println("上传成功！");
        } else {
            System.out.println("上传失败！");
        }
    }

    public void hotBook(){
        System.out.println("****************热卖图书排行榜****************");
        List<BookCount> hotBook = userRentService.getHotBook();
        System.out.println("热卖图书名" + "\t\t" + "热卖数量");
        for (BookCount bookCount : hotBook){
            System.out.println(bookCount.getBook_name() + "\t\t\t" + bookCount.getCount());
        }
    }
}
