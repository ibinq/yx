package com.yx.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yx.bean.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/27 16:10
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {
    Long findParentById(Long id);
}
