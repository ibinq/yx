package com.yx.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yx.bean.Admin;
import com.yx.bean.Menu;
import com.yx.dao.MenuDao;
import com.yx.service.MenuService;
import com.yx.yxcommon.api.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {
    @Resource
    MenuDao menuDao;

    @Override
    public Result list(String query , Integer pageNum,Integer pageSize) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (StringUtils.isNotBlank(query)){
            entityWrapper.like("name",query);
        }
        Page<Admin> page = new Page<>(pageNum, pageSize);//分页类
        List<Admin> list = menuDao.selectPage(page, entityWrapper);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("menus",list);
        map.put("total",page.getTotal());
        return Result.ok(map);
    }

    @Override
    public Result changeStatus(Long id,Integer status) {
        menuDao.changeStatus(id ,status);
        return Result.ok();
    }
}
