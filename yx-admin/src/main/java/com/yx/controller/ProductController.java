package com.yx.controller;

import com.yx.service.ProductService;
import com.yx.vo.ProductVo;
import com.yx.yxcommon.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/5 17:42
 */

@RestController
@RequestMapping("/admin/product")
@Slf4j
@Api(value = "商品",tags = "商品")
public class ProductController     {

    @Autowired
    ProductService productService;

    @GetMapping("list")
    public Result list(){
        System.out.println(123);
        return productService.list();
    }

    @PostMapping("productByCategoryId")
    public Result productByCategoryId(Long id){

        return productService.productByCategoryId(id);
    }

    @PostMapping("search")
    public Result search(String content){

        return productService.search(content);
    }

    @PostMapping("changeStatus")
    @ApiOperation(value = "修改状态",notes = "修改状态",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",required = true,dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "status",required = true,dataType = "Integer")
    })
    public Result changeStatus( Long id,Integer status){
        return productService.changeStatus(id,status);
//        return Result.ok();
    }

    @PostMapping("delete")
    @ApiOperation(value = "删除",notes = "删除",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",required = true,dataType = "Long")
    })
    public Result delete( Long id ){
        return Result.ok(productService.deleteById(id));
//        return Result.ok();
    }

    @PostMapping("add")
    @ApiOperation(value = "添加商品",notes = "添加商品",response = Result.class)
    public Result add(   ProductVo productVo){
        return productService.save(productVo);
    }

    @PostMapping("getById")
    @ApiOperation(value = "查询单个根据id",notes = "查询单个根据id",response = Result.class)
    public Result getById(   Long id){
        return productService.getById(id);
    }
}
