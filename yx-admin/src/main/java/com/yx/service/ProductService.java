package com.yx.service;

import com.baomidou.mybatisplus.service.IService;
import com.yx.bean.Product;
import com.yx.vo.ProductVo;
import com.yx.yxcommon.api.Result;


public interface ProductService extends IService<Product> {

    Result list();

    Result productByCategoryId(Long id);

    Result search(String content);

    Result changeStatus(Long id, Integer status);

    Result save(ProductVo productVo);

    Result getById(Long id);
}
