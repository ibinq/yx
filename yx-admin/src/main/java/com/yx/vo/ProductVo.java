package com.yx.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/6 9:25
 */
@Data
public class ProductVo {
    private Long id;
    private Long categoryId;
    private String title;
    private String subTitle;
    private BigDecimal price;
    private Integer stock;
    private String detail;
    private Boolean status;
    private List<String> mainImageList;
    private List<String> subImageList;
}
