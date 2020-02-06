package com.han.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Books
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/4 0004 17:08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    private Long id;
    private String number;
    private String book_name;
    private String author;
    private Integer amount;
    private Date create_time;
    private Date update_time;
}
