package com.yx.service.impl;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yx.bean.Admin;
import com.yx.bean.Permission;
import com.yx.dao.AdminDao;
import com.yx.jwt.JwtTokenUtil;
import com.yx.security.AdminUserDetails;
import com.yx.service.AdminService;
import com.yx.yxcommon.api.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/13 16:49
 */
@Service("adminService")
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    @Resource
    AdminDao adminDao;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Result list(String query, Integer pageNum, Integer pageSize) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (StringUtils.isNotBlank(query)){
            entityWrapper.like("username",query);
        }
        Page<Admin> page = new Page<>(pageNum, pageSize);//分页类
        List<Admin> list = adminDao.selectPage(page, entityWrapper);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("admins",list);
        map.put("total",page.getTotal());
        return Result.ok(map);
    }

    @Override
    public Result login(HttpServletRequest request, String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            //insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return Result.ok(token);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminDao.getAdminByUsername(username);
    }

    @Override
    public List<Permission> getPermissionList(Long id) {
        return adminDao.getPermissionList(id);
    }

    @Override
    public List<Permission> getPermissionListByUsername(String username) {
        return adminDao.getPermissionListByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
         Admin admin = getAdminByUsername(username);
        if (admin != null) {
            List< Permission> permissionList = getPermissionList(admin.getId());
            return new AdminUserDetails(admin,permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
