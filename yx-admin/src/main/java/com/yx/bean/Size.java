package com.yx.bean;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/13 16:09
 */
@Data
@TableName("ums_size")
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

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;


}
