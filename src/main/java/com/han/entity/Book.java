package com.han.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Book
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/1 0001 19:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;
    private String number;
    private String bookName;
    private String author;
    private Integer amount;
    private Date createTime;
    private Date updateTime;

}
