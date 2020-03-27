package com.yx.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yx.bean.Menu;
import com.yx.bean.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionDao extends BaseMapper<Permission> {
}
