package com.yx.service;


import com.baomidou.mybatisplus.service.IService;
import com.yx.bean.Category;
import com.yx.bean.Permission;
import com.yx.yxcommon.api.Result;

public interface CategoryService extends IService<Category> {
    Result allParents();

    Result allChildren(Long pid);

    Result findParentById(Long id);
}
