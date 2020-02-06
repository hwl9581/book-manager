package com.han.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author HanWL
 * @Since 2020/1/31 0031 14:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String username;
    private String password;
    private String realName;
    private String emailAddress;
    private Integer emailState;
    private Date createTime;
    private Date updateTime;

}
