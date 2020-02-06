package com.han.service;

import com.han.dao.BuyElecBooksDao;
import com.han.dao.ElecBooksDao;
import com.han.dao.UserDao;
import com.han.entity.BuyElecBooks;
import com.han.entity.ElecBooks;
import com.han.entity.User;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ElecBooks
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/5 0005 19:16
 */
public class ElecBooksService {

    private ElecBooksDao elecBooksDao = new ElecBooksDao();
    private UserDao userDao = new UserDao();
    private BuyElecBooksDao buyElecBooksDao = new BuyElecBooksDao();

    public int insertElecBook(ElecBooks elecBooks) {

        String path = elecBooks.getElec_book_address();
        File file = new File(path);
        String uploadDir = "./src/main/resources/books/" + elecBooks.getElec_book_name() + "/";
        File uploadFile = new File(uploadDir);
        System.out.println(uploadDir);
        boolean flag = uploadFile.mkdirs();
        if (flag){
            String[] list = file.list();
            for (String str : list){
                String newpath = path + "\\" + str;
                File itemfile = new File(newpath);
                if (itemfile.isDirectory()){
                    continue;
                } else {
                    String uploadPath = "./src/main/resources/books/" + elecBooks.getElec_book_name() + "/" + str;
                    File chapter = new File(uploadPath);
                    boolean newFile = false;
                    try {
                        newFile = chapter.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (newFile){
                        FileReader fr = null;
                        FileWriter fw = null;
                        try {
                            fr = new FileReader(newpath);
                            fw = new FileWriter(chapter);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        char[] data = new char[512];
                        int length = 0;

                        try {
                            while ((length = fr.read(data)) != -1){
                                fw.write(data, 0, length);
                            }
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        try {
                            fw.close();
                            fr.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("创建文件失败");
                    }
                }
            }
        } else {
            System.out.println("创建目录失败！");
        }
        elecBooks.setElec_book_address(uploadDir);
        int result = 0;
        try {
            result = elecBooksDao.insert(elecBooks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<ElecBooks> selectElecBook(){
        List<ElecBooks> list = new ArrayList<>();
        try {
            list = elecBooksDao.selectAll(ElecBooks.builder().build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ElecBooks> selectElecBook(String username){
        List<BuyElecBooks> buyElecBooksList = null;
        ArrayList<ElecBooks> list = new ArrayList<>();
        User user = userDao.getOneByUsername(username);
        try {
            buyElecBooksList = buyElecBooksDao.selectAll(BuyElecBooks.builder().user_id(user.getId()).build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (BuyElecBooks buyElecBooks : buyElecBooksList){
            ElecBooks elecBooks = null;
            try {
                elecBooks = elecBooksDao.selectOne(ElecBooks.builder().id(buyElecBooks.getElec_book_id()).build());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            list.add(elecBooks);
        }

        return list;
    }

    public ElecBooks selectOneElecBook(String bookName){
        ElecBooks elecBooks = null;
        try {
            elecBooks = elecBooksDao.selectOne(ElecBooks.builder().elec_book_name(bookName).build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return elecBooks;
    }
}
