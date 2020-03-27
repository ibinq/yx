package com.yx.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yx.bean.Menu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuDao extends BaseMapper<Menu> {
    Object changeStatus(Long id, Integer status);
}
