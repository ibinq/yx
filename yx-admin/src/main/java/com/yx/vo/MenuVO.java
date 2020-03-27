package com.yx.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO {
    private Long id;
    private Long parentId;
    private String name;
    private String url;
    private String icon;
    private List<MenuVO> children;
}
