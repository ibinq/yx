package com.yx.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/27 13:02
 */
@Data
public class MenuTreeVO {
    private Long id;
    private Long parentId;
    private String label;
    private List<MenuTreeVO> children;
}
