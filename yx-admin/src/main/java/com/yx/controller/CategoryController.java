package com.yx.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.yx.bean.Category;
import com.yx.bean.Permission;
import com.yx.service.CategoryService;
import com.yx.service.PermissionService;
import com.yx.vo.CategoryVo;
import com.yx.yxcommon.api.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(value = "分类",tags = "分类")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("allParents")
    public  Result allParents(){
        return categoryService.allParents( );
    }

    @PostMapping("allchildren")
    public  Result allChildren(Long pid){
        return categoryService.allChildren(pid );
    }


    @PostMapping("findParentById")
    public  Result findParentById(Long id){
        return categoryService.findParentById(id );
    }
    @GetMapping("/list")
    public Result list(){
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("deleted",0)
                .orderAsc(Lists.newArrayList("sort"));
        List<Category> list = categoryService.selectList(new EntityWrapper());
        LinkedList<CategoryVo> parents = Lists.newLinkedList();
        LinkedList<CategoryVo> children = Lists.newLinkedList();
        for (Category category:list  ) {
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setId(category.getId());
            categoryVo.setName(category.getName());
            categoryVo.setParentId(category.getParentId());
            categoryVo.setImg(category.getImg());
            categoryVo.setSortOrder(category.getSort ());
            categoryVo.setStatus(category.getStatus());
            if (category.getParentId() == 0L){
                parents.add(categoryVo);
            }else {
                children.add(categoryVo);
            }
        }
        for (CategoryVo p : parents ) {
            for (CategoryVo c : children ) {
                if (c.getParentId() == p.getId()){
                    List<CategoryVo> vos = p.getChildren();
                    if (vos == null)
                        vos = new ArrayList<>();
                    vos.add(c);
                    p.setChildren(vos);
                }
            }
        }
        return Result.ok(parents);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Category category){
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setStatus(true);
        category.setDeleted(0);
        return  categoryService.insert(category)?Result.ok():Result.fail();
    }

    @DeleteMapping("{id:\\d+}")
    public Result delete(@PathVariable Long id){
        return  categoryService.deleteById(id)?Result.ok():Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Category category){
        return  categoryService.updateById(category)?Result.ok():Result.fail();
    }
}
