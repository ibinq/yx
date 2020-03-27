package com.yx.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/4 13:10
 */
@Data
public class CategoryVo {
    private Long id;
    private Long parentId;
    private String name;
    private String img;
    private Boolean status;
    private Integer sortOrder;
    private List<CategoryVo> children;
}
