package com.yx.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yx.bean.Admin;
import com.yx.service.AdminService;
import com.yx.service.QiniuService;
import com.yx.service.UserService;
import com.yx.yxcommon.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
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
    QiniuService qiniuService;

    @Autowired
    AdminService adminService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/upload")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if(file.isEmpty()) {
            return "error";
        }
        try {
            String fileUrl=qiniuService.saveImage(file);
            return   fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

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
    @PostMapping("/insertOrUpdate")
    public Result insertOrUpdate( Admin admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setCreateTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        admin.setDeleted(0);
        admin.setStatus(true);
        return adminService.insertOrUpdate(admin) ? Result.ok() : Result.fail();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新用户",notes = "更新用户",response = Result.class)
    public Result update(@RequestBody  Admin admin){
        admin.setUpdateTime(LocalDateTime.now());
        if (adminService.updateById(admin)){
            return Result.ok();
        }
        return Result.fail();
    }
    /**
     * 根据id删除管理员
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('admin:delete')")
    @ApiOperation(value = "根据id删除管理员")
    @DeleteMapping("{id:\\d+}")
    public Result deleteById(@PathVariable  Long id){
        return adminService.deleteById(id) ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "hello demo")
    @GetMapping("hello")
    public Result hello(){
        return Result.ok();
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
    @PreAuthorize("hasAuthority('admin:search')")
    @ApiOperation(value = "根据id查询管理员信息")
    @GetMapping("{id:\\d+}")
    public Result info(@PathVariable Long id){
        return Result.ok(adminService.selectById(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "管理员列表",notes = "管理员列表",response = Result.class)
    public Result list(String query , Integer pageNum,Integer pageSize){
        return  adminService.list(query,pageNum,pageSize);
    }
}
