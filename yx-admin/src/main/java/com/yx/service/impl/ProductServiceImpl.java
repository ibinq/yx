package com.yx.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yx.bean.Product;
import com.yx.dao.ProductDao;
import com.yx.service.ProductService;
import com.yx.vo.ProductVo;
import com.yx.yxcommon.api.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/5 17:44
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {

    @Resource
    ProductDao productDao;

    @Override
    public Result list() {
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        wrapper.eq("deleted",0);
        return Result.ok(productDao.selectList(wrapper));
    }

    @Override
    public Result productByCategoryId(Long id) {
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        wrapper.eq("category_id",id).eq("deleted",0);
        return Result.ok(productDao.selectList(wrapper));
    }

    @Override
    public Result search(String content) {
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        wrapper.like("name",content).eq("deleted",0);
        return Result.ok(productDao.selectList(wrapper));
    }

    @Override
    public Result changeStatus(Long id, Integer status) {
        return Result.ok(productDao.changeStatus(id,status));
    }

    @Override
    public Result save(ProductVo productVo) {
        Product product = new Product();
        product.setTitle(productVo.getTitle());
        product.setCategoryId(productVo.getCategoryId());
        product.setDetail(productVo.getDetail());
        product.setPrice(productVo.getPrice());
        product.setStock(productVo.getStock());
        product.setStatus(productVo.getStatus());
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        product.setDeleted(0);
        product.setSubTitle(productVo.getSubTitle());
        List<String> mainImageList = productVo.getMainImageList();
        StringBuffer buffer = new StringBuffer();
        for (String url :mainImageList ) {
            buffer.append(url);
            buffer.append(",");
        }
        product.setMainImage(buffer.toString());

        List<String> subImageList = productVo.getSubImageList();
        StringBuffer buffer1 = new StringBuffer();
        for (String url :subImageList ) {
            buffer1.append(url);
            buffer1.append(",");
        }
        product.setSubImage(buffer1.toString());
        return Result.ok(productDao.insert(product));
    }

    @Override
    public Result getById(Long id) {
        Product product = productDao.selectById(id);
        ProductVo productVo = new ProductVo();
        productVo.setId(product.getId());
        productVo.setDetail(product.getDetail());
        productVo.setTitle(product.getTitle());
        productVo.setPrice(product.getPrice());
        productVo.setStock(product.getStock());
        productVo.setStatus(product.getStatus());
        productVo.setSubTitle(product.getSubTitle());
        productVo.setMainImageList(Arrays.asList(product.getMainImage().split(",")));
        productVo.setSubImageList(Arrays.asList(product.getSubImage().split(",")));
        return Result.ok(productVo);
    }
}
