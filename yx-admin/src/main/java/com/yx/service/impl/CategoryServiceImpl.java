package com.yx.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.yx.bean.Category;
import com.yx.dao.CategoryDao;
import com.yx.service.CategoryService;
import com.yx.yxcommon.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/27 16:10
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
    @Resource
    CategoryDao categoryDao;
    @Override
    public Result allParents() {
        EntityWrapper<Category> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id",0)
                .eq("status",true)
                .eq("deleted",0)
                .orderBy("sort");
        List<Category> list = categoryDao.selectList(wrapper);
        LinkedList<Category> categories = Lists.newLinkedList(list);
        Category category = new Category();
        category.setId(0L);
        category.setName("无");
        category.setStatus(true);
        category.setDeleted(0);
        categories.addFirst(category);
        return Result.ok(categories);
    }

    @Override
    public Result allChildren(Long pid) {
        EntityWrapper<Category> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id",pid)
                .eq("status",true)
                .eq("deleted",0)
                .orderBy("sort");
        return Result.ok(categoryDao.selectList(wrapper));
    }

    @Override
    public Result findParentById(Long id) {
        Long id1 = categoryDao.findParentById(id);
        return Result.ok(id1);
    }
}
