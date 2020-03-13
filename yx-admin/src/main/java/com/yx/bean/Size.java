package com.yx.bean;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/13 16:09
 */
@Data
public class Size implements Serializable {

    /**
     * 尺寸id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    private String name;

    private Integer sex;

    private Integer height;

    private Integer weight;

    private Integer shoulder;

    private Integer bust;

    private Integer waistline;

    private Integer hipline;

    private Integer footLength;

    private Integer footLine;

    private Integer select;

    private Integer deleted;

    private LocalDateTime creatTime;

    private LocalDateTime updateTime;


}
