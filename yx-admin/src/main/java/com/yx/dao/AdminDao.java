package com.yx.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yx.bean.Admin;
import com.yx.bean.Permission;
import com.yx.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/13 16:39
 */
@Mapper
public interface AdminDao extends BaseMapper<Admin> {
    Admin getAdminByUsername(String name);

    List<Permission> getPermissionList(Long id);

    List<Permission> getPermissionListByUsername(String username);
}
