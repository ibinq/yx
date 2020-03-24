package com.yx.controller;

import com.yx.bean.Size;
import com.yx.service.SizeService;
import com.yx.yxcommon.api.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/19 13:54
 */
@RestController
@RequestMapping("/size")
@Slf4j
@Api(tags = "尺寸")
public class SizeController {

    @Autowired
    SizeService sizeService;

    @PostMapping("/add")
    public Result add(Size size){
        return sizeService.add(size);
    }

    @GetMapping("{id:\\d+}")
    public Result delete(@PathVariable Long id){
        return Result.ok(sizeService.deleteById(id));
    }

    @PostMapping("/edit")
    public Result edit(Size size){
        return sizeService.edit(size);
    }

    @GetMapping("list")
    public Result list(){
        return sizeService.list();
    }


}
