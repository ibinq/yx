package com.yx.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yx.bean.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDao extends BaseMapper<Product> {


    Boolean changeStatus(Long id, Integer status);
}
