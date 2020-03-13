package com.yx.bean;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/13 16:30
 */
@Data
public class Admin implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Boolean deleted;
    private String avatar;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTIme;

}
