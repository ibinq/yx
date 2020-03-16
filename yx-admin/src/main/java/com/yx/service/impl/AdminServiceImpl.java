package com.yx.service.impl;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yx.bean.Admin;
import com.yx.dao.AdminDao;
import com.yx.service.AdminService;
import com.yx.yxcommon.api.Result;
import org.apache.commons.lang3.StringUtils;
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
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    @Resource
    AdminDao adminDao;


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
    public Result login(HttpServletRequest request, String name, String password) {
        //判断用户名是否存在 包括 -> username,phone,email，设计时username，phone，eamil都唯一
        Assert.isNull(name,"用户名不能为空");
        Admin admin = adminDao.getAdminByUsername(name);

        if (admin == null)
            return Result.fail("账号不存在！");
        //密码加密验证

        return Result.ok( );
    }
}
