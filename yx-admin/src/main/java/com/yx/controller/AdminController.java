package com.yx.controller;

import com.yx.bean.Admin;
import com.yx.service.AdminService;
import com.yx.service.UserService;
import com.yx.yxcommon.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/13 16:49
 */
@RestController
@RequestMapping("/admin")
@Slf4j
@Api(tags = "管理员")
public class AdminController {

    @Autowired
    AdminService adminService;


    /**
     * 用户登录 登录名可为username phone email
     * @param request
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码" ,required = true ,dataType = "String")
    })
    public  Result login(HttpServletRequest request, @RequestParam() String username, @RequestParam()String password){
        return adminService.login(request,username,password);
    }

    /**
     * 新增管理员或修改
     * @param admin
     * @return
     */
    @ApiOperation(value = "新增或修改管理员")
    @PostMapping("insertOrUpdate")
    public Result insertOrUpdate(@RequestBody Admin admin){
        return adminService.insertOrUpdate(admin) ? Result.ok() : Result.fail();
    }

    /**
     * 根据id删除管理员
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除管理员")
    @DeleteMapping("{id:\\d+}")
    public Result deleteById(Long id){
        return adminService.deleteById(id) ? Result.ok() : Result.fail();
    }


    /**
     * 获取所有管理员
     * @return
     */
    @ApiOperation(value = "查找管理员")
    @GetMapping("/search")
    public Result search(String query , Integer pageNum,Integer pageSize){
        return adminService.list(query, pageNum, pageSize);
    }

    /**
     * 根据用户id获取管理员信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询管理员信息")
    @GetMapping("{id:\\d+}")
    public Result info(@PathVariable Long id){
        return Result.ok(adminService.selectById(id));
    }

}
