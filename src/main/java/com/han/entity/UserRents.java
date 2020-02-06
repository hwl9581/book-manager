package com.han.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName UserRent
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/1 0001 20:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRents {

    private Long id;
    private Long user_id;
    private String book_number;
    private String book_name;
    private Integer book_amount;
    private Date create_time;
    private Date end_time;
    private Integer book_state;//1：已归还 0：未归还
}
