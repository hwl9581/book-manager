package com.han.controller;

import com.han.entity.Book;
import com.han.entity.Books;
import com.han.entity.User;
import com.han.service.BookService;

import java.util.Scanner;

/**
 * @ClassName BookController
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/1 0001 19:38
 */
public class BookController {

    private Scanner input = new Scanner(System.in);
    private BookService bookService = new BookService();

    public void getBookNumber(){
        System.out.println("请输入图书的名称");
        String bookName = input.next();
        Book book= bookService.getBookNumber(bookName);
        if (book != null){
            System.out.println("图书名称" + "\t\t" + "图书编号" + "\t\t" + "图书库存");
            System.out.println(bookName + "\t\t" + book.getNumber() + "\t\t" + book.getAmount());
        } else {
            System.out.println("没有此图书!!!  ");
        }
    }

    public void rentBook(String username){
        System.out.println("请输入您要借阅的图书名称");
        String bookName = input.next();
        int result = bookService.rent(bookName, username);
        if (result > 0 ){
            System.out.println("借阅成功！请在2周内归还");
        } else {
            System.out.println("借阅失败！！！");
        }
    }

    public void backBook(String username) {
        System.out.println("请输入您要退还的书籍名称：");
        String bookName = input.next();
        int result = bookService.updateBook(bookName, username);
        if (result > 0){
            System.out.println("退还成功！");
        } else {
            System.out.println("退还失败！");
        }
    }
}
