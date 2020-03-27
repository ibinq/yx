package com.yx.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yx.bean.Menu;
import com.yx.service.MenuService;
import com.yx.vo.MenuTreeVO;
import com.yx.vo.MenuVO;
import com.yx.yxcommon.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/menu")
@Slf4j
@Api(value = "菜单",tags = "菜单")
public class MenuController  {

    @Autowired
    MenuService menuService;

    @DeleteMapping("{id:\\d+}")
    @ApiOperation(value = "添加",notes = "添加菜单",response = Result.class)
    public Result delete(@PathVariable Long id){
        return menuService.deleteById(id)?Result.ok():Result.fail();

    }

    @GetMapping("/childMenu")
    public Result childMenu(){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.ne("parent_id",0L);
        return Result.ok(menuService.selectList(entityWrapper));
    }

    @GetMapping("/menuTree")
    public Result menuTree(){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("deleted",0);
        List<Menu> list = menuService.selectList(entityWrapper);
        ArrayList<MenuTreeVO> menuVos = new ArrayList<>();
        ArrayList<MenuTreeVO> children = new ArrayList<>();
        for (Menu m : list) {
            MenuTreeVO menuVo = new MenuTreeVO();
            menuVo.setId(m.getId());
            menuVo.setLabel(m.getName());
            menuVo.setParentId(m.getParentId());
            if (m.getParentId() == 0){
                menuVos.add(menuVo);
            }else {
                children.add(menuVo);
            }
        }
        for (MenuTreeVO menuVo : menuVos){
            for (MenuTreeVO child : children){
                if (menuVo.getId() == child.getParentId()){
                    List<MenuTreeVO> vos = menuVo.getChildren();
                    if (vos == null)
                        vos =  new ArrayList<MenuTreeVO>();
                    vos.add(child);
                    menuVo.setChildren(vos);
                }
            }
        }


        return Result.ok(menuVos);
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加",notes = "添加菜单",response = Result.class)
    public Result save(@RequestBody Menu menu){
        menu.setCreateTime(LocalDateTime.now());
        menu.setUpdateTime(LocalDateTime.now());
        menu.setDeleted(0);
        menu.setStatus(true);
        if(menuService.insert(menu)){
            return Result.ok();
        }
        return Result.fail();
    }

    @GetMapping("/menus")
    @ApiOperation(value = "列表",notes = "菜单列表",response = Result.class)
    public Result menus(String query , Integer pageNum,Integer pageSize){
        return menuService.list(query,pageNum,pageSize);
    }
    @GetMapping("/parents")
    public Result parents( ){
        EntityWrapper<Menu> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id",0);
        List<Menu> list = menuService.selectList(wrapper);
        Menu menu = new Menu();
        menu.setId(0L);
        menu.setName("无");
        list.add(menu);
        return Result.ok(list);
    }
    @PostMapping("changeStatus")
    @ApiOperation(value = "修改状态",notes = "修改状态",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",required = true,dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "status",required = true,dataType = "Integer")
    })
    public Result changeStatus( Long id,Integer status){
        return menuService.changeStatus(id,status);
//        return Result.ok();
    }
    @GetMapping("/list")
    @ApiOperation(value = "列表",notes = "菜单列表",response = Result.class)
   // @RequiresPermissions("admin:search")
    public Result list(){

        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("deleted",0);
        List<Menu> list = menuService.selectList(entityWrapper);
        ArrayList<MenuVO> menuVos = new ArrayList<>();
        ArrayList<MenuVO> children = new ArrayList<>();
        for (Menu m : list) {
            MenuVO menuVo = new MenuVO();
            menuVo.setIcon(m.getIcon());
            menuVo.setId(m.getId());
            menuVo.setName(m.getName());
            menuVo.setUrl(m.getUrl());
            menuVo.setParentId(m.getParentId());
            if (m.getParentId() == 0){
                menuVos.add(menuVo);
            }else {
                children.add(menuVo);
            }
        }
        for (MenuVO menuVo : menuVos){
            for (MenuVO child : children){
                if (menuVo.getId() == child.getParentId()){
                    List<MenuVO> vos = menuVo.getChildren();
                    if (vos == null)
                        vos =  new ArrayList<MenuVO>();
                    vos.add(child);
                    menuVo.setChildren(vos);
                }
            }
        }
        return  Result.ok(menuVos);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Menu m){
        return Result.ok(menuService.updateById(m));
    }
}
