package com.yx.service;

import com.baomidou.mybatisplus.service.IService;
import com.yx.bean.Menu;
import com.yx.yxcommon.api.Result;

public interface MenuService extends IService<Menu> {
    Result list(String query , Integer pageNum,Integer pageSize);

    Result changeStatus(Long id, Integer status);
}
