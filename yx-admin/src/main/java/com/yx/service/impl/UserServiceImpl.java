package com.yx.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yx.bean.User;
import com.yx.dao.UserDao;
import com.yx.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/13 16:49
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
