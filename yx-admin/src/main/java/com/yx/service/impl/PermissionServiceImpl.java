package com.yx.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yx.bean.Permission;
import com.yx.dao.PermissionDao;
import com.yx.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/27 13:38
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {
}
