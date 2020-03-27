package com.yx.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yx.bean.Menu;
import com.yx.bean.Permission;
import com.yx.service.MenuService;
import com.yx.service.PermissionService;
import com.yx.vo.MenuTreeVO;
import com.yx.vo.MenuVO;
import com.yx.yxcommon.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/permission")
@Slf4j
@Api(value = "权限",tags = "权限")
public class PermissionController  {
    @Autowired
    PermissionService permissionService;

    @GetMapping("{id:\\d+}")
    public Result list(@PathVariable Long id){
        EntityWrapper<Permission> wrapper = new EntityWrapper<>();
        wrapper.eq("menu_id",id);
        return Result.ok(permissionService.selectList(wrapper));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Permission permission){
        permission.setCreateTime(LocalDateTime.now());
        permission.setUpdateTime(LocalDateTime.now());
        permission.setStatus(true);
        permission.setDeleted(0);
        return  permissionService.insert(permission)?Result.ok():Result.fail();
    }

    @DeleteMapping("{id:\\d+}")
    public Result delete(@PathVariable Long id){
        return  permissionService.deleteById(id)?Result.ok():Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Permission permission){
        return  permissionService.updateById(permission)?Result.ok():Result.fail();
    }
}
