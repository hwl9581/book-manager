package com.han.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName ElecBooks
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/5 0005 19:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElecBooks {
    private Long id;
    private String elec_book_name;
    private String elec_book_author;
    private String elec_book_address;
    private Date create_time;
}
