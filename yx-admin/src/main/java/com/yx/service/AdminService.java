package com.yx.service;

import com.baomidou.mybatisplus.service.IService;
import com.yx.bean.Admin;
import com.yx.bean.Permission;
import com.yx.bean.User;
import com.yx.yxcommon.api.Result;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/13 16:47
 */
public interface AdminService extends IService<Admin> {

    Result list(String query, Integer pageNum, Integer pageSize);

    Result login(HttpServletRequest request, String username, String password);

    Admin getAdminByUsername(String username);

    List<Permission> getPermissionList(Long id);

    List<Permission> getPermissionListByUsername(String username);

    UserDetails loadUserByUsername(String username);
}
