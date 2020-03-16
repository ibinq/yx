package com.yx.controller;

import com.yx.service.UserService;
import com.yx.yxcommon.api.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/13 16:49
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    @GetMapping("{id:\\d+}")
    public Result info(@PathVariable Long id){
        return Result.ok(userService.selectById(id));
    }

}
