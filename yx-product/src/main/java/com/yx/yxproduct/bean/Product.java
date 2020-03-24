package com.yx.yxproduct.bean;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/19 14:24
 */
@Data
@TableName("pms_product")
public class Product implements Serializable {

    private Long id;

    private Long categoryId;

    private String title;

    private String subTitle;

    private BigDecimal price;

    private String mainImage;

    private String subImage;

    private Integer stock;

    private String detail;

    private Boolean coupon;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
