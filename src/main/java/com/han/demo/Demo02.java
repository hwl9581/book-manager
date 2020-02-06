package com.han.demo;

import java.io.File;

/**
 * @ClassName Demo02
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/5 0005 20:17
 */
public class Demo02 {

    public static void main(String[] args){

        File dir = new File("./src/main/resources/dir");
        boolean mkdirs = dir.mkdirs();
        System.out.println(mkdirs);

    }

}
