package com.yx.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品
 */
@Data
@TableName("pms_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 4960017291273018583L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long categoryId;

    private String title;

    private String subTitle;

    private String mainImage;

    private String subImage;

    private String detail;
    //价格
    private BigDecimal price;

    //库存
    private Integer stock;
    //状态
    private Boolean status;

    private Integer coupon;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;

}