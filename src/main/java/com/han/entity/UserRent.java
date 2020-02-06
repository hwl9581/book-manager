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
public class UserRent {

    private Long id;
    private Long userId;
    private String bookNumber;
    private String bookName;
    private Integer bookAmount;
    private Date createTime;
    private Date endTime;
    private Integer bookState;//1：已归还 0：未归还
}
